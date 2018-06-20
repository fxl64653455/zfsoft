package com.zfsoft.api.web.filter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zfsoft.api.web.WebContext;
import com.zfsoft.api.web.session.User;
import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.cache.core.Cache;
import com.zfsoft.web.servlet.filter.cache.CacheSupportedFilter;

/**
 * 
 * <p>
 *   <h3>zftal框架<h3>
 *   说明：最近使用过滤器
 * <p>
 * @author <a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a>
 * @version 2017年1月13日上午10:41:19
 */
public class RecentlyUsedFilter extends CacheSupportedFilter {

	private static final int USED_MENU_MAX = 6;
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		String gnmkdm = servletRequest.getParameter("gnmkdmKey");
		//访问功能菜单，处理业务逻辑
		if (!StringUtils.isNull(gnmkdm)){
			Cache<String,List<RecentlyUsedMenu>> info = getCacheManager().getCache(getCacheName());
//			Map<String,List<RecentlyUsedMenu>> info = new HashMap<String,List<RecentlyUsedMenu>>();
			
			User user = WebContext.getUser();
			if (user == null) return;
			
			RecentlyUsedMenu menu = new RecentlyUsedMenu();
			menu.setGnmkdm(gnmkdm);
			menu.setLastTime(new Date());
			
			if (info.get(user.getYhm()) != null){
				List<RecentlyUsedMenu> menuList = info.get(user.getYhm());
				addUsedMenu(menuList, menu);
				info.put(user.getYhm(), menuList);
			} else {
				List<RecentlyUsedMenu> menuList = new ArrayList<RecentlyUsedMenu>();
				menuList.add(menu);
				info.put(user.getYhm(), menuList);
			}
		}
		chain.doFilter(servletRequest, servletResponse);
	}

	
	private void addUsedMenu(List<RecentlyUsedMenu> list,RecentlyUsedMenu menu){
		boolean isUsed = false;
		for (RecentlyUsedMenu usedMenu : list){
			if (usedMenu.getGnmkdm().equals(menu.getGnmkdm())){
				usedMenu.setCount(usedMenu.getCount()+1);
				usedMenu.setLastTime(new Date());
				isUsed = true;
			}
		}
		
		if (!isUsed){
			list.add(menu);
		}
		//按最近使用时间排序 
		Collections.sort(list,new Comparator<RecentlyUsedMenu>() {
			public int compare(RecentlyUsedMenu o1, RecentlyUsedMenu o2) {
				return o2.getLastTime().compareTo(o1.getLastTime());
			}
		});
		
		if (list.size() > USED_MENU_MAX){
			list.remove(list.size()-1);
		}
		 
	}
	
}


class RecentlyUsedMenu implements Serializable{
	private static final long serialVersionUID = 4208912013646294464L;
	
	private String gnmkdm;
	private Date lastTime;
	private int count;
	
	public String getGnmkdm() {
		return gnmkdm;
	}
	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String toString(){
		return this.gnmkdm;
	}
}