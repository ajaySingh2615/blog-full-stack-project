package com.cadt.blog.service.helpers;

import com.cadt.blog.common.util.SlugUtils;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class SlugService {

    public String unique(String baseTitle, Predicate<String> exists) {
        String base = SlugUtils.toSlug(baseTitle);
        String candidate = base;
        int i = 2;
        while (exists.test(candidate)) candidate = base + "-" + i++;
        return candidate;
    }
}
