package br.com.dev.ecommerce.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ResourceServerConfig {

	@Value("${cors.origins}")
	private String corsOrigins;


	/*
	 * Configuração específica para o console do H2 Database
	 * - @Profile("test"): Ativo apenas em ambiente de teste
	 * - @Order(1): Maior prioridade na cadeia de filtros
	 *
	 * Configurações:
	 * - securityMatcher: Aplica apenas às URLs do H2 Console
	 * - csrf: Desabilita proteção CSRF para o console
	 * - frameOptions: Permite que o console seja exibido em iframe
	 */
	@Bean
	@Profile("test")
	@Order(1)
	public SecurityFilterChain h2SecurityFilterChain(HttpSecurity http) throws Exception {

		http.securityMatcher(PathRequest.toH2Console()).csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
		return http.build();
	}

	/**
	 * Configuração de segurança para a aplicação principal.
	 *
	 * - @Order(3): Prioridade mais baixa do que o filtro do H2.
	 * - CSRF é desativado para simplificar.
	 * - Todas as requisições são permitidas (`permitAll`) para desenvolvimento inicial.
	 * - Configuração do servidor de recursos OAuth2 com suporte a JWT.
	 * - Configuração de CORS para requisições de origens permitidas.
	 *
	 * @param http Objeto de configuração de segurança.
	 * @return Cadeia de filtros de segurança para a aplicação principal.
	 * @throws Exception Caso haja erro na configuração.
	 */
	@Bean
	@Order(3)
	public SecurityFilterChain rsSecurityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
		http.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()));
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		return http.build();
	}

	/**
	 * Configuração do conversor de autenticação JWT.
	 *
	 * - Usa o claim "authorities" do token JWT para definir as permissões do usuário.
	 * - Remove o prefixo padrão "ROLE_" para manter os nomes das permissões sem alteração.
	 *
	 * @return Configuração personalizada do JwtAuthenticationConverter.
	 */
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
		grantedAuthoritiesConverter.setAuthorityPrefix("");

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}


	/**
	 * Configuração de CORS (Cross-Origin Resource Sharing).
	 *
	 * - Define origens permitidas com base na configuração do arquivo de propriedades.
	 * - Permite métodos HTTP padrão, como POST, GET, PUT, DELETE, PATCH.
	 * - Permite cabeçalhos necessários para autenticação e requisições.
	 *
	 * @return Fonte de configuração de CORS.
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {

		String[] origins = corsOrigins.split(",");

		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOriginPatterns(Arrays.asList(origins));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

	/**
	 * Registro do filtro de CORS com prioridade alta.
	 *
	 * - Usa a configuração de CORS definida anteriormente.
	 * - Define a ordem de execução como prioridade mais alta.
	 *
	 * @return Registro do filtro de CORS.
	 */
	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
