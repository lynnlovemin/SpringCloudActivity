package com.lynn.blog.comment;

import com.lynn.blog.common.app.Application;
import com.lynn.blog.pub.PublicApplication;

public class CommentApplication extends PublicApplication {

    public static void main(String[] args) {
        Application.startup(CommentApplication.class,args);
    }
}
