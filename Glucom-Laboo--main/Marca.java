package TrabPOO;

public class Marca {
   private static int proximoCodigo = 1;
   private int codigo;
   private String nomeFantasia;
   private String fabricante;
   private String cnpj;

   private Marca(int codigo, String nomeFantasia, String fabricante, String cnpj) {
      this.codigo = codigo;
      this.nomeFantasia = nomeFantasia;
      this.fabricante = fabricante;
      this.cnpj = cnpj;
   }

   public static Marca criarMarca(String nomeFantasia, String fabricante, String cnpj) {
      if (nomeFantasia != null && fabricante != null && cnpj != null) {
         int cod = proximoCodigo++;
         return new Marca(cod, nomeFantasia, fabricante, cnpj);
      } else {
         return null;
      }
   }

   public int getCodigo() {
      return this.codigo;
   }

   public String getNomeFantasia() {
      return this.nomeFantasia;
   }

   public void setNomeFantasia(String nomeFantasia) {
      this.nomeFantasia = nomeFantasia;
   }

   public String getFabricante() {
      return this.fabricante;
   }

   public void setFabricante(String fabricante) {
      this.fabricante = fabricante;
   }

   public String getCnpj() {
      return this.cnpj;
   }

   public void setCnpj(String cnpj) {
      this.cnpj = cnpj;
   }
}
