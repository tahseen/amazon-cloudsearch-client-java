package aws.services.cloudsearchv2.search;

/**
 * Specifies a field that you want to get facet information for—FIELD is the name of the field. The specified field must be facet enabled in the domain configuration. Facet options are specified as a JSON object. If the JSON object is empty, facet.FIELD={}, facet counts are computed for all field values, the facets are sorted by facet count, and the top 10 facets are returned in the results.
 * 
 * You can specify three options in the JSON object:
 * 
 * sort specifies how you want to sort the facets in the results: bucket or count. Specify bucket to sort alphabetically or numerically by facet value (in ascending order). Specify count to sort by the facet counts computed for each facet value (in descending order). To retrieve facet counts for particular values or ranges of values, use the buckets option instead of sort.
 * 
 * buckets specifies an array of the facet values or ranges you want to count. Buckets are returned in the order they are specified in the request. To specify a range of values, use a comma (,) to separate the upper and lower bounds and enclose the range using brackets or braces. A square brace, [ or ], indicates that the bound is included in the range, a curly brace, { or }, excludes the bound. You can omit the upper or lower bound to specify an open-ended range. When omitting a bound, you must use a curly brace. The sort and size options are not valid if you specify buckets.
 * 
 * size specifies the maximum number of facets to include in the results. By default, Amazon CloudSearch returns counts for the top 10. The size parameter is only valid when you specify the sort option; it cannot be used in conjunction with buckets.
 * 
 * For example, the following request gets facet counts for the year field, sorts the facet counts by value and returns counts for the top three:
 * 
 * facet.year={sort:"bucket", size:3}
 * To specify which values or range of values you want to calculate facet counts for, use the buckets option. For example, the following request calculates and returns the facet counts by decade:
 * 
 * facet.year={buckets:["[1970,1979]","[1980,1989]",
 *              "[1990,1999]","[2000,2009]",
 *              "[2010,}"]}
 * You can also specify individual values as buckets:
 * 
 * facet.genres={buckets:["Action","Adventure","Sci-Fi"]}
 * Note that the facet values are case-sensitive—with the sample IMDB movie data, if you specify ["action","adventure","sci-fi"] instead of ["Action","Adventure","Sci-Fi"], all facet counts are zero.
 * 
 * Required: No
 * 
 * @author Tahseen Ur Rehman Fida
 * @email tahseen.ur.rehman@gmail.com
 */
public class Facet {
	/**
	 * Specifies a field that you want to get facet information
	 */
	public String field;
	
	/**
	 * sort specifies how you want to sort the facets in the results: bucket or count. Specify bucket to sort alphabetically or numerically by facet value (in ascending order). Specify count to sort by the facet counts computed for each facet value (in descending order). To retrieve facet counts for particular values or ranges of values, use the buckets option instead of sort.
	 */
	public String sort;
	
	/**
	 * buckets specifies an array of the facet values or ranges you want to count. Buckets are returned in the order they are specified in the request. To specify a range of values, use a comma (,) to separate the upper and lower bounds and enclose the range using brackets or braces. A square brace, [ or ], indicates that the bound is included in the range, a curly brace, { or }, excludes the bound. You can omit the upper or lower bound to specify an open-ended range. When omitting a bound, you must use a curly brace. The sort and size options are not valid if you specify buckets.
	 */
	public String buckets;
	
	/**
	 * size specifies the maximum number of facets to include in the results. By default, Amazon CloudSearch returns counts for the top 10. The size parameter is only valid when you specify the sort option; it cannot be used in conjunction with buckets.	 */
	public Integer size;
}
