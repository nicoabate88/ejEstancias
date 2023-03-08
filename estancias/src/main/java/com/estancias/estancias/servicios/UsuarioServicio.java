package com.estancias.estancias.servicios;

import com.estancias.estancias.entidades.Usuario;
import com.estancias.estancias.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String alias) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarAlias(alias);
         
        String tipo = usuario.getClass().getName();
        if(tipo.contains("Admin")){
            tipo = "Admin";
        } if(tipo.contains("Familia")){
            tipo = "Familia";
        } if(tipo.contains("Cliente")){
            tipo = "Cliente";
        }
               
        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+tipo);

            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); //usuario loguado: aqui instanciamos con Spring Security

            HttpSession session = attr.getRequest().getSession(true); //usuario loguado: lo instanciamos con http

            session.setAttribute("usuariosession", usuario); // usuario loguado: lo seteamos y preparamos en usuariosession para poder pasarlo a clase controlador
          
            return new User(usuario.getAlias(), usuario.getClave(), permisos);

        } else {
            return null;
        }

    }
    
  
}
