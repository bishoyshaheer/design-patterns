package org.example.pattern;

abstract class Cpu{}

abstract class Memory{}

class IntelCPU extends Cpu{}

class IntelMemory extends Memory{}

class AmdCPU extends Cpu{}

class AmdMemory extends Memory{}

enum Vendor {
    INTEL, AMD
}

abstract class AbstractFactory{


    abstract Cpu createCpu();
    abstract Memory createMemory();

    private static final IntelFactory INTEL_FACTORY = new IntelFactory();
    private static final AmdFactory AMD_FACTORY = new AmdFactory();

    public static AbstractFactory getFactory(Vendor vendor){
        if(vendor == Vendor.INTEL){
            return INTEL_FACTORY;
        }else if(vendor == Vendor.AMD){
            return AMD_FACTORY;
        }
        throw new IllegalArgumentException("Unsupported vendor "+vendor);
    }

}

class IntelFactory extends AbstractFactory{
    @Override
    Cpu createCpu() {
        return new IntelCPU();
    }

    @Override
    Memory createMemory() {
        return new IntelMemory();
    }
}

class AmdFactory extends AbstractFactory{
    @Override
    Cpu createCpu() {
        return new AmdCPU();
    }

    @Override
    Memory createMemory() {
        return new AmdMemory();
    }
}

public class AbstractFactoryClient {
    public static void main(String[] args) {
        AbstractFactory factory = AbstractFactory.getFactory(Vendor.INTEL);
        Cpu cpu = factory.createCpu();

        Memory memory = factory.createMemory();
    }
}
