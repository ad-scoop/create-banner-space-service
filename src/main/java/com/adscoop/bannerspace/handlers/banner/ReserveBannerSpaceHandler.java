package com.adscoop.bannerspace.handlers.banner;

<<<<<<< HEAD
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 19/11/2016.
 */
public class ReserveBannerSpaceHandler implements Handler{




    @Override
    public void handle(Context ctx) throws Exception {

        String userToken = ctx.getPathTokens().get("userBannerToken");
        String bannerid = ctx.getPathTokens().get("bannerid");

        if(!userToken.isEmpty()&&!bannerid.isEmpty()){


=======
import com.adscoop.bannerspace.entites.BannerNode;
import com.adscoop.bannerspace.entites.UserNode;
import com.adscoop.bannerspace.services.BannerNodeService;
import com.adscoop.bannerspace.services.UserService;
import com.google.inject.Inject;
import io.netty.buffer.ByteBuf;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Optional;

import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 19/11/2016.
 */
public class ReserveBannerSpaceHandler implements Handler {

    private UserService userService;
    private BannerNodeService bannerNodeService;


    @Inject
    public ReserveBannerSpaceHandler(UserService userService, BannerNodeService bannerNodeService) {
        this.userService = userService;
        this.bannerNodeService = bannerNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String email = ctx.getPathTokens().get("email");
        Long bannerid = Long.valueOf(ctx.getPathTokens().get("bid"));
        if(!email.isEmpty()&&bannerid !=null){
            Optional<UserNode> webSiteUser = userService.findUserByEmail(email);
            Optional<BannerNode>  bannerNode = bannerNodeService.findbyId(bannerid);

            if(webSiteUser.isPresent()&&bannerNode.isPresent()){
                BannerNode node = bannerNode.get();
                node.getBannerSpaceToken().add(webSiteUser.get().getToken());
                bannerNodeService.saveOrUpdate(node);
               Optional<UserNode> bannerNodeUser = userService.findUserbyBannerId(bannerNode.get().getId());
                ctx.render(json(bannerNodeUser.get()));

            } else {
                ctx.render(json("Banner or user does not exist"));
            }
>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251


        }else{
            ctx.render(json("email or banner id is missing in path"));
        }

        }

    }
}
