package com.java1234.filter;
 
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * Filter是Zuul的核心，用来实现对外服务的控制。
 * Filter的生命周期有4个，分别是“PRE”、“ROUTING”、“POST”、“ERROR”
 * http://www.ityouknow.com/springcloud/2018/01/20/spring-cloud-zuul.html
 */
public class AccessFilter extends ZuulFilter{
 
    Logger logger=Logger.getLogger(AccessFilter.class);
     
    /**
     * 判断该过滤器是否要被执行
     */
    @Override
    public boolean shouldFilter() {
        // TODO Auto-generated method stub
        return true;
    }
 
    /**
     * 过滤器的具体执行逻辑
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String parameter = request.getParameter("accessToken");
        logger.info(request.getRequestURL().toString()+" 请求访问");
        if(parameter==null){
            logger.error("accessToken为空！");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"accessToken is empty!\"}");
            return null;
        }
        //  token判断逻辑
        logger.info(request.getRequestURL().toString()+" 请求成功");

//        if (StringUtils.isNotBlank(token)) {
//            ctx.setSendZuulResponse(true); //对请求进行路由
//            ctx.setResponseStatusCode(200);
//            ctx.set("isSuccess", true);
//            return null;
//        } else {
//            ctx.setSendZuulResponse(false); //不对其进行路由
//            ctx.setResponseStatusCode(400);
//            ctx.setResponseBody("token is empty");
//            ctx.set("isSuccess", false);
//            return null;
//        }
        return null;
    }
 
    /**
     * 过滤器的类型 这里用pre，代表会再请求被路由之前执行
     * 有pre、route、post、error四种
     */
    @Override
    public String filterType() {
        // TODO Auto-generated method stub
        return "pre";
    }
 
    /**
     * 过滤器的执行顺序
     * 数字越小表示顺序越高，越先执行
     */
    @Override
    public int filterOrder() {
        // TODO Auto-generated method stub
        return 0;
    }
 
}