package org.example;

import java.util.Objects;

public class MemerVo {
    int id;
    String user_id;
    String password;
    String address;

     public MemerVo(int id, String user_id, String password, String address) {
          this.id = id;
          this.user_id = user_id;
          this.password = password;
          this.address = address;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getUser_id() {
          return user_id;
     }

     public void setUser_id(String user_id) {
          this.user_id = user_id;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          MemerVo memerVo = (MemerVo) o;
          return id == memerVo.id && Objects.equals(user_id, memerVo.user_id) && Objects.equals(password, memerVo.password) && Objects.equals(address, memerVo.address);
     }

     @Override
     public int hashCode() {
          return Objects.hash(id, user_id, password, address);
     }

     @Override
     public String toString() {
          return "MemerVo{" +
                  "id=" + id +
                  ", user_id='" + user_id + '\'' +
                  ", password='" + password + '\'' +
                  ", address='" + address + '\'' +
                  '}';
     }
}
