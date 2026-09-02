package TrabPOO;

public class Usuario {
   private static int proximoId = 1;
   private int id;
   private String nome;
   private String nomeUsuario;
   private String senha;
   private TipoUsuario tipo;

   private Usuario(int id, String nome, String nomeUsuario, String senha, TipoUsuario tipo) {
      this.setId(id);
      this.setNome(nome);
      this.setNomeUsuario(nomeUsuario);
      this.setSenha(senha);
      this.setTipo(tipo);
   }

   public static Usuario criarUsuario(String nome, String nomeUsuario, String senha, TipoUsuario tipo) {
      if (nome != null && nomeUsuario != null && senha != null && tipo != null) {
         int novoId = proximoId++;
         return new Usuario(novoId, nome, nomeUsuario, senha, tipo);
      } else {
         return null;
      }
   }

   public TipoUsuario getTipo() {
      return this.tipo;
   }

   public void setTipo(TipoUsuario tipo) {
      this.tipo = tipo;
   }

   public String getSenha() {
      return this.senha;
   }

   public void setSenha(String senha) {
      this.senha = senha;
   }

   public String getNomeUsuario() {
      return this.nomeUsuario;
   }

   public void setNomeUsuario(String nomeUsuario) {
      this.nomeUsuario = nomeUsuario;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public static int getProximoId() {
      return proximoId;
   }

   public static void setProximoId(int proximoId) {
      Usuario.proximoId = proximoId;
   }
}
