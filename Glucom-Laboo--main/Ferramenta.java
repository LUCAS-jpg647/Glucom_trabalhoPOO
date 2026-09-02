package TrabPOO;

public class Ferramenta {
   private Marca marca;
   private boolean status;
   private String nome;
   private String caracteristicas;

   public String getCaracteristicas() {
      return this.caracteristicas;
   }

   public void setCaracteristicas(String caracteristicas) {
      this.caracteristicas = caracteristicas;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public boolean isStatus() {
      return this.status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public Marca getMarca() {
      return this.marca;
   }

   public void setMarca(Marca marca) {
      this.marca = marca;
   }
}
