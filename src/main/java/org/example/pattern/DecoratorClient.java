package org.example.pattern;
interface Shipping{
    int getFee();
}
class SimpleShipping implements Shipping{
    private int fee;

    public SimpleShipping(int fee) {
        this.fee = fee;
    }

    @Override
    public int getFee() {
        return fee;
    }
}
class ShippingDecorator implements Shipping{
    private Shipping shipping;

    ShippingDecorator(Shipping shipping){
        this.shipping = shipping;
    }

    @Override
    public int getFee() {
        return shipping.getFee();
    }
}

class FreeShipping extends ShippingDecorator{
    FreeShipping(Shipping shipping){
        super(shipping);
    }

    @Override
    public int getFee() {
        return 0;
    }
}

class InternationalShipping extends ShippingDecorator{
    InternationalShipping(Shipping shipping){
        super(shipping);
    }

    @Override
    public int getFee() {
        return super.getFee()+100;
    }
}
public class DecoratorClient {
    public static void main(String[] args) {
        Shipping shipping = new InternationalShipping(new InternationalShipping(new FreeShipping(new SimpleShipping(10))));
        System.out.println("shipping cost(200) : " + shipping.getFee());
    }
}
