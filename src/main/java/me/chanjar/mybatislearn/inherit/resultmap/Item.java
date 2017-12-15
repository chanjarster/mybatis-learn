package me.chanjar.mybatislearn.inherit.resultmap;

public class Item {

  private Integer id;
  private String title;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Item item = (Item) o;

    if (id != null ? !id.equals(item.id) : item.id != null) return false;
    return title != null ? title.equals(item.title) : item.title == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Item{");
    sb.append("id=").append(id);
    sb.append(", title='").append(title).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
