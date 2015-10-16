package org.hitis.util;

import java.util.List;

/*
 * 分页类
 * @author  collonn
 * 必要时，继承该类，重写getMutiPageInfo()方法就可以了
 */
public class HitisPager {
	/*
	 * 每页列表，即每页显示的记录
	 */
	private List list;

	/*
	 * 当前页码，即当前第几页
	 */
	private int curPage = 1;

	/*
	 * 每页记录数 pagesize
	 */
	private int pageSize = 15;

	/*
	 * 总记录数
	 */
	private int totalRow = 0;

	//请求路径
	private String requestURI;

	//请求的所有参数(request域)
	private String allParam;
	
	//分布页脚table的css中的class名
	private String cssClassName = "formTable";

	public String getCssClassName() {
		return cssClassName;
	}

	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	/*
	 * 计算总页数
	 */
	public int getTotalPage() {
		int t = this.totalRow / this.pageSize;
		return this.totalRow % this.pageSize == 0 ? t : t + 1;
	}

	/*
	 * 当前第n-m条记录
	 */
	public String getCurrentListNum() {
		int startNum = this.pageSize * (this.curPage - 1) + 1;
		int endNum = 0;
		if (this.curPage == this.getTotalPage()) {
			endNum = this.totalRow;
		} else {
			endNum = startNum + this.pageSize - 1;
		}

		return "[" + startNum + "-" + endNum + "]";
	}

	/*
	 * 如果是oracle，由于oracle用select * from where rownum between getOffsetStart() and getOffsetEnd()
	 * oracle是从1开始的
	 */
	public int getOffsetStart() {
		return (this.curPage - 1) * this.pageSize + 1;
	}

	public int getOffsetEnd() {
		return this.getOffsetStart() + this.pageSize - 1;
	}

	public String getAllParam() {
		return allParam;
	}

	public void setAllParam(String allParam) {
		this.allParam = allParam;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	private String getPageRange() {
		String range = "";
		range += this.getOffsetStart();
		range += "-";
		if (this.curPage == this.getTotalPage()) {
			range += this.totalRow;
		} else {
			range += this.getOffsetEnd();
		}

		return range;
	}

	private int getPrevPage() {
		if (this.curPage == 1) {
			return 1;
		} else {
			return this.curPage - 1;
		}
	}

	private int getNextPage() {
		if (this.curPage == this.getTotalPage()) {
			return this.getTotalPage();
		} else {
			return this.curPage + 1;
		}
	}

	private String getUrlWithParam() {
		String url = "";
		url += this.getRequestURI() + "?" + this.getAllParam();
		return url;
	}

	public String getMutiPageInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("<table class=\"" + this.cssClassName + "\"><tr>");
		sb.append("<td height=\"25\" align=\"left\">");
		sb.append("共[" + this.getTotalPage() + "]页 第[" + this.curPage + "]页");
		sb.append("</td>");

		sb.append("<td height=\"25\" align=\"center\">");
		sb.append("<a href=\"####\" onclick=\"pagerTableGoToPage();\">转到</a>");
		sb.append("<input type=\"text\" id=\"toPage\" style=\"width:30px;\"/>");
		sb.append("<script type=\"text/javascript\">");
		sb.append("function pagerTableGoToPage(){");
		sb.append("var toPage = document.getElementById('toPage').value;");
		sb.append("if(toPage == ''){alert('请输入页数'); return false;}");
		sb.append("if(!/^-{0,1}\\d{1,}$/.test(toPage)){alert('请输入数字'); return false;}");
		sb.append("toPage = parseInt(toPage);");
		sb.append("var totalPage = " + this.getTotalPage() + ";");
		sb.append("if(toPage < 1 || toPage > totalPage){alert('页码越界'); return false;}");
		//sb.append("alert('" + this.getRequestURI() + "'); return false;");
		sb.append("window.location.href = '" + this.getUrlWithParam() + "&page=' + toPage;");
		sb.append("}");
		sb.append("</script>");
		sb.append("</td>");

		sb.append("<td height=\"25\" align=\"center\">");
		sb.append("当前记录/总记录数[" + this.getPageRange() + "/" + this.totalRow + "]");
		sb.append("</td>");

		sb.append("<td height=\"25\" align=\"right\">");
		if (this.getTotalPage() == 1) {
			sb.append("首页 ");
			sb.append("上一页 ");
			sb.append("下一页 ");
			sb.append("尾页 ");
		} else if (this.curPage == 1) {
			sb.append("首页 ");
			sb.append("上一页 ");
			sb.append("<a href=\"" + this.getUrlWithParam() + "&page=" + this.getNextPage() + "\">下一页</a> ");
			sb.append("<a href=\"" + this.getUrlWithParam() + "&page=" + this.getTotalPage() + "\">尾页</a>");
		} else if (this.curPage == this.getTotalPage()) {
			sb.append("<a href=\"" + this.getUrlWithParam() + "&page=" + 1 + "\">首页</a> ");
			sb.append("<a href=\"" + this.getUrlWithParam() + "&page=" + this.getPrevPage() + "\">上一页</a> ");
			sb.append("下一页 ");
			sb.append("尾页");
		} else {
			sb.append("<a href=\"" + this.getUrlWithParam() + "&page=" + 1 + "\">首页</a> ");
			sb.append("<a href=\"" + this.getUrlWithParam() + "&page=" + this.getPrevPage() + "\">上一页</a> ");
			sb.append("<a href=\"" + this.getUrlWithParam() + "&page=" + this.getNextPage() + "\">下一页</a> ");
			sb.append("<a href=\"" + this.getUrlWithParam() + "&page=" + this.getTotalPage() + "\">尾页</a>");
		}
		sb.append("</td>");
		sb.append("</tr></table>");
		return sb.toString();
	}
}