package com.dsdev.blade.mvc.controller;

import com.dsdev.blade.mvc.model.User;
import com.google.errorprone.annotations.FormatMethod;
import com.hellokaton.blade.annotation.Path;
import com.hellokaton.blade.annotation.request.Body;
import com.hellokaton.blade.annotation.request.Form;
import com.hellokaton.blade.annotation.route.GET;
import com.hellokaton.blade.annotation.route.POST;
import com.hellokaton.blade.mvc.RouteContext;
import com.hellokaton.blade.mvc.http.Request;
import com.hellokaton.blade.mvc.ui.ResponseType;
import com.hellokaton.blade.mvc.ui.RestResponse;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.hellokaton.anima.Anima.save;
import static com.hellokaton.anima.Anima.select;

@Path(responseType = ResponseType.JSON)
public class IndexController {

    @GET
    public void index(RouteContext ctx) {
        List<User> userList = select().from(User.class).all();
        ctx.attribute("users", userList);
        ctx.render("hello.html");
        //return "hello.html";
    }


    @GET("users")
    public RestResponse users() {
        return RestResponse.ok(select().from(User.class).all());
    }

    @POST("save")
    public RestResponse postUser(@Body User user){
        return RestResponse.ok(save(user));
    }

}
