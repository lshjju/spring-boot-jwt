@Entity
@ToString
@Getter
@Setter
@Table(indexes = @Index(columList = "title", name = "작명"))
public class Item {

  @Id @GeneratedValue(stragegy = GenerationType.IDENTITY)
  public Long id;
  
  private String title;
  private Integer price;

  // public void setTitle(String title) {
  //   만약에 title변수에 이상한 긴 문자 넣으려고 하면 차단하기~~
  //   this.title = title;
  // }
  
}
