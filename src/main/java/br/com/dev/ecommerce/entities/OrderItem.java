package br.com.dev.ecommerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// A classe OrderItem representa o item de um pedido de e-commerce.
// Ela une um pedido (Order) a um produto (Product), armazenando a quantidade do produto no pedido e o preço unitário.
// Utiliza uma chave composta, representada por OrderItemPK, para referenciar essas associações de maneira única.
@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    // A anotação @EmbeddedId indica que a chave primária é composta por múltiplas colunas.
    // Neste caso, a chave composta está sendo gerida pela classe OrderItemPK, que armazena a referência do pedido (Order)
    // e do produto (Product). Isso permite a combinação única de um pedido específico com um produto específico.
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK(); // Instancia o ID composto com uma nova instância vazia de OrderItemPK.

    @Getter
    @Setter
    private int quantity;

    @Getter
    @Setter
    private double price;

    public OrderItem() {
    }

    // Construtor com parâmetros que recebe o pedido, o produto, a quantidade e o preço.
    // Este construtor facilita a criação de uma instância de OrderItem com todos os valores necessários.
    public OrderItem(Order order, Product product, int quantity, double price) {

        // Atribui o pedido à chave composta, que armazena a referência ao pedido que contém este item.
        id.setOrder(order);

        // Atribui o produto à chave composta, indicando qual produto específico faz parte deste item de pedido.
        id.setProduct(product);

        // Atribui a quantidade fornecida ao campo quantity. Isso define quantas unidades deste produto estão sendo
        // incluídas no pedido.
        this.quantity = quantity;

        // Atribui o preço unitário do produto ao campo price. Isso é importante para manter a integridade dos preços
        // em relação ao valor cobrado no momento da compra.
        this.price = price;
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }


}
