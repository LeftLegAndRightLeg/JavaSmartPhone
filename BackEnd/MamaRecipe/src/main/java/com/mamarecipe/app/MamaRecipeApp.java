package com.mamarecipe.app;

/**
 * Created by Jeremiah on 7/29/15.
 */
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Michal Gajdos (michal.gajdos at oracle.com)
 */
@ApplicationPath("/")
public class MamaRecipeApp extends ResourceConfig {

    public MamaRecipeApp() {
        super(MultiPartFeature.class);
    }
}