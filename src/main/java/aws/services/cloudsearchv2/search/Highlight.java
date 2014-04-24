package aws.services.cloudsearchv2.search;

/**
 * Retrieves highlights for matches in the specified text or text-array field. Highlight options are specified as a JSON object. If the JSON object is empty, the returned field text is treated as HTML and the first match is highlighted with emphasis tags: <em>search-term</em>.
 * 
 * You can specify four options in the JSON object:
 * 
 * format—specifies the format of the data in the text field: text or html. When data is returned as HTML, all non-alphanumeric characters are encoded. The default is html.
 * 
 * max_phrases—specifies the maximum number of occurrences of the search term(s) you want to highlight. By default, the first occurrence is highlighted.
 * 
 * pre_tag—specifies the string to prepend to an occurrence of a search term. The default for HTML highlights is <em>. The default for text highlights is *.
 * 
 * post_tag—specifies the string to append to an occurrence of a search term. The default for HTML highlights is </em>. The default for text highlights is *.
 * 
 * Examples: highlight.plot={}, highlight.plot={format:'text',max_phrases:2,pre_tag:'<b>',post_tag:'</b>'}
 * 
 * Required: No
 * 
 * 
 * @author Tahseen Ur Rehman Fida
 * @email tahseen.ur.rehman@gmail.com
 *
 */
public class Highlight {
	public String field;
	/**
	 * specifies the format of the data in the text field: text or html. When data is returned as HTML, all non-alphanumeric characters are encoded. The default is html.
	 */
	public String format;
	/**
	 * specifies the maximum number of occurrences of the search term(s) you want to highlight. By default, the first occurrence is highlighted.
	 */
	public Integer maxPhrases;
	/**
	 * specifies the string to prepend to an occurrence of a search term. The default for HTML highlights is <em>. The default for text highlights is *.
	 */
	public String preTag;
	/**
	 * specifies the string to append to an occurrence of a search term. The default for HTML highlights is </em>. The default for text highlights is *.
	 */
	public String postTag;
}
