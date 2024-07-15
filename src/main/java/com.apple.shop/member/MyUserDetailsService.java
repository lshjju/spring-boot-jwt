import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    var result = memberRepository.findByUsername(username);
    if (result.isEmpty()){
      throw new UsernameNotFoundException("그런 아이디 없음");    
    }
    var user = result.get();
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAutority("일반유저"));
    var a = new Customer(user.getUsername(), user.getPassword(), authorities);
    a.displayName = user.getDisplayName();
    a.id = user.getId();
    
    return a;

  } 

}


