package com.techsolutions.patterns.iterator;

import com.techsolutions.model.Product;

public interface ProductIterator {
    boolean hasNext();
    Product next();
}