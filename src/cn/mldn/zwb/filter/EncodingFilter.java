package cn.mldn.zwb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//下面演示：在配置文件中对编码的修改
		String encoding = filterConfig.getServletContext().getInitParameter("encoding");
		request.setCharacterEncoding(encoding);//实际上这样就可以了，但是通常将编码的设置放在配置文件当中，这样子便于修改-----------关键
		System.out.println("----------------------------------------------------");
		chain.doFilter(request, response);
	}

	private FilterConfig filterConfig = null;
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		this.filterConfig=fConfig;
	}

}