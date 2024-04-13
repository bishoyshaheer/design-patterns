package org.example.pattern;

interface ProductService {
    boolean containProduct(int productId);
}

class ProductServiceImpl implements ProductService {
    int[] products;
    Search search;

    public ProductServiceImpl(Search search) {
        this.search = search;
        products = new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    }

    @Override
    public boolean containProduct(int productId) {
        return search.find(products, productId);
    }
}

interface Search {
    boolean find(int[] arr, int i);
}

class LinearSearch implements Search {
    @Override
    public boolean find(int[] arr, int i) {
        System.out.println("linear search steps: ");
        for (int i1 : arr) {
            System.out.print("| ");
            if (i1 == i) {
                return true;
            }
        }
        return false;
    }
}

class BinarySearch implements Search {
    @Override
    public boolean find(int[] arr, int i) {
        System.out.println("binary search steps: ");
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            System.out.print("| ");
            int mid = (l + r) / 2;
            if (arr[mid] == i) {
                return true;
            } else if (arr[mid] < i) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}

public class StrategyClient {
    public static void main(String[] args) {
        ProductService productService = new ProductServiceImpl(new LinearSearch());
        System.out.println(productService.containProduct(21));
        productService = new ProductServiceImpl(new BinarySearch());
        System.out.println(productService.containProduct(21));
    }
}
