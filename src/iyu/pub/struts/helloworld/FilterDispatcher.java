package iyu.pub.struts.helloworld;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FilterDispatcher
 */
@WebFilter("*.action")
public class FilterDispatcher implements Filter {

    /**
     * Default constructor. 
     */
    public FilterDispatcher() {
        // TODO Auto-generated constructor stub
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy......");
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("doFilter......");
		
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		//1.获取servletPath
		String servletPath = req.getServletPath();
		System.out.println(servletPath);
		
		String path = null;
		
		//2.判断servletPath，若其等于"/product-input.action",则转发到/WEB-INF/pages/input.jsp
		if("/product-input.action".equals(servletPath)){
			path = "/WEB-INF/pages/input.jsp";
		}
		
		//3.若其等于"/product-save.action",则
		if("/product-save.action".equals(servletPath)){
			//1)获取请求参数
			String productName = request.getParameter("productName");
			String productDesc = request.getParameter("productDesc");
			String productPrice = request.getParameter("productPrice");
			
			//2)把请求信息封装为一个Product对象
			Product product = new Product(null, productName, productDesc, Double.parseDouble(productPrice));
//			Product product = new Product();
			
			//3)执行保存操作
			System.out.println("Save Product:"+product);
			product.setProductId(1001);
//			product.setProductName("dddd");
//			product.setProductDesc("zasssssssssss");
//			product.setProductPrice(12222);
			
			//4)把Product对象保存到request中，${param.productName} -> ${requestScope.product.productName}
			request.setAttribute("product", product);
			
			path="/WEB-INF/pages/details.jsp";
		}
		
		if(path != null){
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init......");
		
	}

}
