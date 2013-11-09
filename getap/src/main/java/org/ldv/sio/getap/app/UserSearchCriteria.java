package org.ldv.sio.getap.app;

/**
 * Form backing bean to hold search details
 */
public class UserSearchCriteria {

  /**
   * The free form search query
   */
  private String query;

  /**
   * Construct an empty criteria
   */
  public UserSearchCriteria() {
    this("");
  }

  /**
   * Construct criteria with the provided query
   * 
   * @param query
   */
  public UserSearchCriteria(String query) {
    this.query = query;
  }

  /**
   * Get the query
   * 
   * @return query
   */
  public String getQuery() {
    return query;
  }

  /**
   * Set the query
   * 
   * @param query
   */
  public void setQuery(String query) {
    if (query == null)
      query = "";
    this.query = query;
  }
}