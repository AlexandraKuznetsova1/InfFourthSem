package ru.itis.resale.javalabresale.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.resale.javalabresale.enums.Role;
import ru.itis.resale.javalabresale.enums.State;
import ru.itis.resale.javalabresale.models.Account;
import ru.itis.resale.javalabresale.repositories.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email.equals("sasha@gmail.com")) {
            Account account = Account.builder()
                    .email("sasha@gmail.com")
                    .id(10L)
                    .firstName("Sasha")
                    .lastName("Kuznetsova")
                    .role(Role.ADMIN)
                    .state(State.CONFIRMED)
                    .password("$2a$12$XnuWxjA8HHoZgi6.OIYZL.EcR4p8nmFEg2EV1H5FeGp0Ep3typ07i")
                    .build();

            return new AccountUserDetails(account);
        } else throw new UsernameNotFoundException("User not found");
    }
}
