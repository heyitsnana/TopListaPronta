package com.toplistapronta.app;
package TopLista;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {
    private List<Usuario> usuarios;
    private List<Mercado> mercados;
    private List<Item> itens;
    private Usuario usuarioLogado;
    private Scanner scanner;

    public Menu() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin", "admin", true));
        mercados = new ArrayList<>();
        itens = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new Menu().start();
    }

    public void start() {
        while (true) {
            if (usuarioLogado == null) {
                exibirMenuInicial();
                String opcao = scanner.nextLine().trim();
                switch (opcao) {
                    case "1": entrar(); break;
                    case "2": cadastrarUsuario(); break;
                    case "3":
                        System.out.println("Saindo do sistema. Até logo!");
                        return;
                    default:
                        System.out.println("Opção inválida.\n");
                }
            } else {
                if (usuarioLogado.isAdmin()) {
                    adminMenu();
                } else {
                    usuarioMenu();
                }
            }
        }
    }

    private void exibirMenuInicial() {
        System.out.println("===== MENU =====");
        System.out.println("1. Entrar");
        System.out.println("2. Cadastrar usuário");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void entrar() {
        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        Usuario user = autenticar(login, senha);
        if (user != null) {
            switch (user.getStatus()) {
                case DESATIVADA:
                    System.out.println("Conta desativada.\n"); break;
                case EXCLUIDA:
                    System.out.println("Conta excluída.\n"); break;
                default:
                    usuarioLogado = user;
                    System.out.println("\nLogin bem-sucedido! Bem-vindo, " + user.getLogin() + ".\n");
            }
        } else {
            System.out.println("Login ou senha incorretos.\n");
        }
    }

    private Usuario autenticar(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }

    private void cadastrarUsuario() {
        System.out.print("Novo login: ");
        String novoLogin = scanner.nextLine().trim();
        System.out.print("Nova senha: ");
        String novaSenha = scanner.nextLine().trim();
        if (existeLogin(novoLogin)) {
            System.out.println("Falha no cadastro: login já existe.\n");
        } else {
            usuarios.add(new Usuario(novoLogin, novaSenha, false));
            System.out.println("Cadastro realizado com sucesso! Agora faça login.\n");
        }
    }

    private void cadastrarAdmin() {
        System.out.print("Novo login (admin): ");
        String novoLogin = scanner.nextLine().trim();
        System.out.print("Nova senha: ");
        String novaSenha = scanner.nextLine().trim();
        if (existeLogin(novoLogin)) {
            System.out.println("Falha no cadastro: login já existe.\n");
        } else {
            usuarios.add(new Usuario(novoLogin, novaSenha, true));
            System.out.println("Cadastro de administrador realizado com sucesso!\n");
        }
    }

    private void cadastrarMercado() {
        System.out.print("Nome do mercado: ");
        String nome = scanner.nextLine().trim();
        System.out.print("CEP: ");
        String cep = scanner.nextLine().trim();
        System.out.print("Complemento: ");
        String complemento = scanner.nextLine().trim();

        Mercado m = new Mercado(nome, cep, complemento);

        do {
            System.out.print("Endereço: ");
            String end = scanner.nextLine().trim();
            System.out.print("Número: ");
            String numero = scanner.nextLine().trim();
            System.out.print("Latitude: ");
            double lat = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Longitude: ");
            double lon = Double.parseDouble(scanner.nextLine().trim());
            m.addLocalizacao(end, numero, lat, lon);

            System.out.print("Adicionar outra localização? (s/n): ");
        } while (scanner.nextLine().trim().equalsIgnoreCase("s"));

        mercados.add(m);
        System.out.println("Mercado '" + nome + "' cadastrado com sucesso!\n");
    }

    private void alterarMercado() {
        if (mercados.isEmpty()) {
            System.out.println("Nenhum mercado cadastrado.\n");
            return;
        }
        System.out.println("\n--- Mercados Cadastrados ---");
        for (int i = 0; i < mercados.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, mercados.get(i).getNome());
        }
        System.out.print("Escolha o número do mercado para alterar: ");
        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (idx < 0 || idx >= mercados.size()) {
            System.out.println("Índice inválido.\n");
            return;
        }
        Mercado m = mercados.get(idx);

        System.out.println("O que deseja alterar?");
        System.out.println("1. Nome");
        System.out.println("2. CEP");
        System.out.println("3. Complemento");
        System.out.println("4. Localizações");
        System.out.print("Opção: ");
        String op = scanner.nextLine().trim();

        switch (op) {
            case "1":
                System.out.print("Novo nome: ");
                m.setNome(scanner.nextLine().trim());
                System.out.println("Nome atualizado.\n");
                break;
            case "2":
                System.out.print("Novo CEP: ");
                m.setCep(scanner.nextLine().trim());
                System.out.println("CEP atualizado.\n");
                break;
            case "3":
                System.out.print("Novo complemento: ");
                m.setComplemento(scanner.nextLine().trim());
                System.out.println("Complemento atualizado.\n");
                break;
            case "4":
                if (m.getLocalizacoes().isEmpty()) {
                    System.out.println("Não há localizações cadastradas.\n");
                    return;
                }
                System.out.println("\n--- Localizações de " + m.getNome() + " ---");
                for (int i = 0; i < m.getLocalizacoes().size(); i++) {
                    Mercado.Localizacao loc = m.getLocalizacoes().get(i);
                    System.out.printf("%d. %s, Nº: %s (lat: %.6f, long: %.6f)%n",
                        i + 1, loc.getEndereco(), loc.getNumero(), loc.getLatitude(), loc.getLongitude());
                }
                System.out.print("Escolha a localização para alterar: ");
                int locIdx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (locIdx < 0 || locIdx >= m.getLocalizacoes().size()) {
                    System.out.println("Índice inválido.\n");
                    return;
                }
                System.out.print("Novo endereço: ");
                String novoEnd = scanner.nextLine().trim();
                System.out.print("Novo número: ");
                String novoNum = scanner.nextLine().trim();
                System.out.print("Nova latitude: ");
                double novaLat = Double.parseDouble(scanner.nextLine().trim());
                System.out.print("Nova longitude: ");
                double novaLon = Double.parseDouble(scanner.nextLine().trim());
                m.updateLocalizacao(locIdx, novoEnd, novoNum, novaLat, novaLon);
                System.out.println("Localização atualizada.\n");
                break;
            default:
                System.out.println("Opção inválida.\n");
        }
    }

    private void excluirMercado() {
        if (mercados.isEmpty()) {
            System.out.println("Nenhum mercado cadastrado.\n");
            return;
        }
        System.out.println("\n--- Mercados Cadastrados ---");
        for (int i = 0; i < mercados.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, mercados.get(i).getNome());
        }
        System.out.print("Escolha o número do mercado para excluir: ");
        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (idx < 0 || idx >= mercados.size()) {
            System.out.println("Índice inválido.\n");
            return;
        }
        Mercado m = mercados.get(idx);
        System.out.print("Confirma exclusão de '" + m.getNome() + "'? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            mercados.remove(idx);
            System.out.println("Mercado excluído.\n");
        } else {
            System.out.println("Operação cancelada.\n");
        }
    }

    private void visualizarMercados() {
        if (mercados.isEmpty()) {
            System.out.println("Nenhum mercado cadastrado.\n");
            return;
        }
        System.out.println("\n--- Lista de Mercados ---");
        for (Mercado m : mercados) {
            System.out.println(m);
        }
    }


    private void cadastrarItem() {
        System.out.print("Nome do item: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine().trim();
        System.out.print("Validade (AAAA-MM-DD): ");
        LocalDate validade = LocalDate.parse(scanner.nextLine().trim());

        Item novoItem = new Item(nome, quantidade, preco, descricao, validade);
        itens.add(novoItem);

        System.out.println("Item cadastrado com sucesso!\n");
    }

    private void alterarItem() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.\n");
            return;
        }

        System.out.println("--- Itens Cadastrados ---");
        for (int i = 0; i < itens.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, itens.get(i).getNome());
        }

        System.out.print("Escolha o número do item para alterar: ");
        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;

        if (idx < 0 || idx >= itens.size()) {
            System.out.println("Índice inválido.\n");
            return;
        }

        Item item = itens.get(idx);

        System.out.println("1. Nome\n2. Quantidade\n3. Preço\n4. Descrição\n5. Validade");
        System.out.print("Escolha o campo para alterar: ");
        String op = scanner.nextLine().trim();

        switch (op) {
            case "1": System.out.print("Novo nome: "); item.setNome(scanner.nextLine().trim()); break;
            case "2": System.out.print("Nova quantidade: "); item.setQuantidade(Integer.parseInt(scanner.nextLine().trim())); break;
            case "3": System.out.print("Novo preço: "); item.setPreco(Double.parseDouble(scanner.nextLine().trim())); break;
            case "4": System.out.print("Nova descrição: "); item.setDescricao(scanner.nextLine().trim()); break;
            case "5": System.out.print("Nova validade (AAAA-MM-DD): "); item.setValidade(LocalDate.parse(scanner.nextLine().trim())); break;
            default: System.out.println("Opção inválida."); return;
        }

        System.out.println("Item alterado com sucesso.\n");
    }

    private void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.\n");
            return;
        }
        System.out.println("\n--- Itens Cadastrados ---");
        for (int i = 0; i < itens.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, itens.get(i));
        }
        System.out.println("------------------------\n");
    }

    private void visualizarItens() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item disponível.\n");
            return;
        }

        System.out.println("\n--- Itens Disponíveis ---");
        for (Item item : itens) {
            System.out.println(item);
        }
        System.out.println();
    }


    private void cadastrarItemComprado() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item disponível para compra.\n");
            return;
        }

        System.out.println("\n--- Itens Disponíveis para Comprar ---");
        for (int i = 0; i < itens.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, itens.get(i).getNome());
        }
        System.out.print("Escolha o número do item comprado: ");
        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (idx < 0 || idx >= itens.size()) {
            System.out.println("Índice inválido.\n");
            return;
        }

        Item itemBase = itens.get(idx);
        System.out.print("Informe a quantidade comprada: ");
        int quantidade = Integer.parseInt(scanner.nextLine().trim());

    
        Item itemComprado = new Item(itemBase.getNome(), quantidade, itemBase.getPreco(),
                                     itemBase.getDescricao(), itemBase.getValidade());
        usuarioLogado.addItemComprado(itemComprado);
        System.out.println("Item registrado no histórico de compras!\n");
    }


    private void verRecomendacoes() {
        List<Item> historico = usuarioLogado.getItensComprados();
        if (historico.isEmpty()) {
            System.out.println("Nenhuma recomendação disponível. Compre algo para receber recomendações!\n");
            return;
        }
       
        Map<String, List<Item>> agrupados = historico.stream()
                .collect(Collectors.groupingBy(Item::getNome));

        System.out.println("\n📌 Recomendações com base em suas compras:");
        for (String nome : agrupados.keySet()) {
            List<Item> itensDoTipo = agrupados.get(nome);
            int soma = 0;
            for (Item i : itensDoTipo) soma += i.getQuantidade();
            double media = (double) soma / itensDoTipo.size();
            System.out.printf("- %s | Quantidade recomendada: %.1f (baseado em %d compras)\n",
                    nome, media, itensDoTipo.size());
        }
        System.out.println();
    }

    private void adminMenu() {
        while (usuarioLogado != null) {
            System.out.println("===== MENU ADMINISTRADOR =====");
            System.out.println("1. Criar administrador");
            System.out.println("2. Cadastrar mercado");
            System.out.println("3. Alterar mercado");
            System.out.println("4. Excluir mercado");
            System.out.println("5. Listar usuários");
            System.out.println("6. Cadastrar item");
            System.out.println("7. Alterar item");
            System.out.println("8. Listar itens");
            System.out.println("9. Logout");
            System.out.print("Escolha uma opção: ");
            String op = scanner.nextLine().trim();

            switch (op) {
                case "1": cadastrarAdmin(); break;
                case "2": cadastrarMercado(); break;
                case "3": alterarMercado(); break;
                case "4": excluirMercado(); break;
                case "5": listarUsuarios(); break;
                case "6": cadastrarItem(); break;
                case "7": alterarItem(); break;
                case "8": listarItens(); break;
                case "9":
                    System.out.println("Logout realizado.\n");
                    usuarioLogado = null;
                    break;
                default:
                    System.out.println("Opção inválida.\n");
            }
        }
    }

    private void usuarioMenu() {
        while (usuarioLogado != null) {
            System.out.println("===== MENU USUÁRIO =====");
            System.out.println("1. Visualizar mercados");
            System.out.println("2. Ver itens disponíveis");
            System.out.println("3. Registrar item comprado");
            System.out.println("4. Ver recomendações");
            System.out.println("5. Logout");
            System.out.print("Escolha uma opção: ");
            String op = scanner.nextLine().trim();

            switch (op) {
                case "1": visualizarMercados(); break;
                case "2": visualizarItens(); break;
                case "3": cadastrarItemComprado(); break;
                case "4": verRecomendacoes(); break;
                case "5":
                    usuarioLogado = null;
                    System.out.println("Logout realizado.\n");
                    break;
                default:
                    System.out.println("Opção inválida.\n");
            }
        }
    }

    private void listarUsuarios() {
        System.out.println("\n--- Usuários Cadastrados ---");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
        System.out.println("----------------------------\n");
    }

    private boolean existeLogin(String login) {
        return usuarios.stream().anyMatch(u -> u.getLogin().equals(login));
    }

    private Usuario findUsuario(String login) {
        return usuarios.stream()
                       .filter(u -> u.getLogin().equals(login))
                       .findFirst()
                       .orElse(null);
    }
}
