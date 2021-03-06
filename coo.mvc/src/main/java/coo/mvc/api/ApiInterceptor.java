package coo.mvc.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * API接口拦截器。
 */
public class ApiInterceptor extends HandlerInterceptorAdapter {
  @Value("${coo.api.log.enabled:false}")
  private Boolean logEnabled = false;

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    ModelMap modelMap = modelAndView.getModelMap();
    ApiReply reply = ApiUtils.getReply(modelMap);
    modelMap.clear();
    modelMap.addAttribute(reply);
    if (logEnabled) {
      ApiUtils.log(request, reply);
    }
  }
}
