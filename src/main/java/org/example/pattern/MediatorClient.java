package org.example.pattern;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GetOrderById {
    int orderId;
    public GetOrderById(int orderId) {
        this.orderId = orderId;
    }
}
class OrderDto{
    int orderId;
    List<Integer> productIds;

    public OrderDto(int orderId, List<Integer> productIds) {
        this.orderId = orderId;
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", productIds=" + productIds +
                '}';
    }
}
class PlaceOrder {
    int productId;
    int userId;
    int quantity;

    public PlaceOrder(int productId, int userId, int quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "productId=" + productId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                '}';
    }
}

/*T type, R return*/
interface QueryHandler<T, R> {
     R handle(T query);
}
interface CommandHandler<T> {
    void handle(T command);
}

class GetOrderByIdQueryHandler implements QueryHandler<GetOrderById, OrderDto> {
    @Override
    public OrderDto handle(GetOrderById query) {
        System.out.println("finding order by id: " + query.orderId);
        return new OrderDto(1, List.of(91,92));
    }
}
class PlaceOrderCommandHandler implements CommandHandler<PlaceOrder> {
    @Override
    public void handle(PlaceOrder command) {
        System.out.println("processing command: " + command);
    }
}

class Mediator {
    Map<Class<?>, CommandHandler> commandHandlers = new HashMap<>();
    Map<Class<?>, QueryHandler> queryHandlers = new HashMap<>();

    public Mediator() {
        queryHandlers.put(GetOrderById.class, new GetOrderByIdQueryHandler());
        commandHandlers.put(PlaceOrder.class, new PlaceOrderCommandHandler());
    }

    public <T> void send(T command) {
        if (commandHandlers.containsKey(command.getClass())) {
            CommandHandler<T> handler = commandHandlers.get(command.getClass());
            if (handler != null) {
                handler.handle(command);
                return;
            }
        }
        throw new RuntimeException("no command handler found for command: " + command.getClass());
    }

    public <T,R> R get(T query) {
        if (queryHandlers.containsKey(query.getClass())) {
            QueryHandler<T, R> handler = queryHandlers.get(query.getClass());
            if (handler != null) {
                return handler.handle(query);
            }
        }
        throw new RuntimeException("no handler found for query: " + query);
    }
}

public class MediatorClient {
    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        mediator.send(new PlaceOrder(1,2,3));
        OrderDto orderDto = mediator.get(new GetOrderById(100));
        System.out.println("oder found by id:100 \n\t"+orderDto);
    }
}
