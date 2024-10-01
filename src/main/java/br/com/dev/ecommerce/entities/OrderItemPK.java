package br.com.dev.ecommerce.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


// A classe OrderItemPK define a chave primária composta de um item de pedido (OrderItem).
// Ela combina um pedido (Order) e um produto (Product) para garantir que cada par
// pedido-produto seja único dentro de um pedido.
// Essa classe é usada pela classe OrderItem para representar a chave composta.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable  //A anotação @Embeddable indica que essa classe será incorporada em outra como uma chave primária.
@EqualsAndHashCode
public class OrderItemPK {

    // A anotação @ManyToOne indica que muitos itens de pedido podem estar associados a um único pedido (Order).
    // O campo "order" referencia o pedido ao qual este item pertence.
    // A anotação @JoinColumn define qual coluna na tabela de OrderItem faz o mapeamento com a tabela de Order,
    // neste caso, a coluna "order_id".
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // Representa o pedido ao qual o item está vinculado.

    // A anotação @ManyToOne também é usada aqui para indicar que muitos itens de pedido podem estar associados
    // a um único produto (Product).
    // A anotação @JoinColumn define a coluna na tabela de OrderItem que faz o mapeamento com a tabela de Product,
    // neste caso, a coluna "product_id".
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // Representa o produto específico no pedido.

    // Construtores gerados pela anotação @AllArgsConstructor (para um construtor com todos os parâmetros)
    // e @NoArgsConstructor (para um construtor sem parâmetros).


}
