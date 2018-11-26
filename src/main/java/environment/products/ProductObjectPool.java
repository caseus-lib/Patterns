package environment.products;

import java.util.Stack;

public class ProductObjectPool {

    private static Stack<MagicalProduct> stack = new Stack<>();

    private static ProductObjectPool ourInstance = new ProductObjectPool();

    public static ProductObjectPool getInstance() {
        return ourInstance;
    }

    private ProductObjectPool() {
    }

    public synchronized MagicalProduct asquire() {
        if (stack.isEmpty()) {
            stack.add(new MagicalProduct());
            System.out.println("add new in pool");
        }
        return stack.pop();
    }

    public synchronized void release(MagicalProduct product) {
        product.reset();
        stack.add(product);
    }

}
