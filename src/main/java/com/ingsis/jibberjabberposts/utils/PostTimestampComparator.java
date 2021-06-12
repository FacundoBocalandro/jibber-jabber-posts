package com.ingsis.jibberjabberposts.utils;

import com.ingsis.jibberjabberposts.model.Post;

import java.util.Comparator;

public class PostTimestampComparator implements Comparator<Post> {

    @Override
    public int compare(Post post1, Post post2) {
        return post2.getTimestamp().compareTo(post1.getTimestamp());
    }
}
