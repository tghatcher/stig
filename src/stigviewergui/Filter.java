/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

public class Filter {
	private FilterType myFilter = null;
	private String FilterText = "";
	private Boolean isExclude = false;

	public String GetFilterText() {
		return this.FilterText;
	}

	public FilterType GetFilterType() {
		return this.myFilter;
	}

	public boolean bGetIsExcludeFilter() {
		return this.isExclude;
	}

	public void SetFilterType(FilterType ft) {
		this.myFilter = ft;
	}

	public void SetFilterType(FilterType ft, boolean bIsExclude) {
		this.myFilter = ft;
		this.isExclude = bIsExclude;
	}

	public void SetFilterText(String s) {
		this.FilterText = s;
	}

	public static enum FilterType {
		Keyword, Title, STIGID, RULEID, VULID, CATI, CATII, CATIII, IACONTROLS, CCIREF;

		private FilterType() {
		}
	}

}
