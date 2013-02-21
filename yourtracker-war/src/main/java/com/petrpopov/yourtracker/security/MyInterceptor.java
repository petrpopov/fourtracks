package com.petrpopov.yourtracker.security;

import com.petrpopov.yourtracker.service.FoursquareDefaultBean;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.social.foursquare.api.Foursquare;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

/**
 * User: petrpopov
 * Date: 19.02.13
 * Time: 22:39
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(! (handler instanceof HandlerMethod))
            return super.preHandle(request, response, handler);

        HandlerMethod method = (HandlerMethod) handler;
        Object bean = method.getBean();

        if(bean == null)
            return super.preHandle(request, response, handler);

        try {
            Field[] fields = bean.getClass().getDeclaredFields();

            for(Field f : fields)
            {
                Class<?> type = f.getType();
                if( !type.equals(Foursquare.class))
                    continue;

                f.setAccessible(true);
                Object api = f.get(bean);
                if( api == null ) {
                    System.out.println("API IS NULL, HA-HA !");
                    return super.preHandle(request, response, handler);
                }

                //TargetClassAware aware = (TargetClassAware)api;

                if(AopUtils.isAopProxy(api) && api instanceof Advised) {
                    Object target = ((Advised)api).getTargetSource().getTarget();

                    if( target instanceof FoursquareDefaultBean) {
                        System.out.println("NOT CONNECTED TO FOURSQUARE !");

                        RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
                        dispatcher.forward(request, response);
                        return false;

                        //return super.preHandle(request, response, handler);
                    }
                    //FoursquareDefaultBean fs = (FoursquareDefaultBean)target;


                }

              /*  if( api instanceof FoursquareDefaultBean ) {
                    System.out.println("NOT CONNECTED TO FOURSQUARE !");
                    return super.preHandle(request, response, handler);
                }*/
            }

        }
        catch (Exception e) {
            throw e;
        }

        return super.preHandle(request, response, handler);
    }
}
