package com.demo.unit.adaptor;

import com.demo.unit.adaptor.dto.RestUserPost;

public interface RestAdaptor {
    RestUserPost findPostById(Long id);
}
