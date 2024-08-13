public class ArvoreBinaria {
    private No raiz; // Campo privado para armazenar a raiz da árvore binária

    public ArvoreBinaria() {
        this.raiz = null; // Construtor inicializa a árvore com a raiz nula
    }

    // Método para inserir um novo valor na árvore binária
    public void inserir(int valor) {
        No novoNo = new No(valor); // Cria um novo nó com o valor fornecido
        if (this.raiz == null) {
            this.raiz = novoNo; // Se a árvore estiver vazia, define o novo nó como a raiz
        } else {
            No atual = this.raiz; // Começa na raiz
            No pai = null; // Nó pai
            boolean esquerda = false; // Flag para rastrear a direção (esquerda/direita)
            while (atual != null) {
                if (novoNo.getValor() < atual.getValor()) {
                    pai = atual; // Move para o filho esquerdo
                    atual = atual.getEsq();
                    esquerda = true;
                } else {
                    pai = atual; // Move para o filho direito
                    atual = atual.getDir();
                    esquerda = false;
                }
            }
            if (esquerda) {
                pai.setEsq(novoNo); // Insere o novo nó como filho esquerdo
            } else {
                pai.setDir(novoNo); // Insere o novo nó como filho direito
            }
        }
    }

    // Método para remover um nó folha com o valor fornecido
    public void removerFolha(int valor) {
        raiz = removerFolhaRec(raiz, valor); // Chama o método recursivo de remoção
    }

    // Método auxiliar recursivo para remover um nó folha
    private No removerFolhaRec(No no, int valor) {
        if (no == null) {
            return null; // Valor não encontrado
        }

        if (valor < no.getValor()) {
            no.setEsq(removerFolhaRec(no.getEsq(), valor)); // Recorre na subárvore esquerda
        } else if (valor > no.getValor()) {
            no.setDir(removerFolhaRec(no.getDir(), valor)); // Recorre na subárvore direita
        } else {
            // Nó encontrado
            if (no.getEsq() == null && no.getDir() == null) {
                // É uma folha
                return null; // Remove a folha
            } else {
                // Não é uma folha
                return no; // Retorna o nó, pois não pode ser removido
            }
        }
        return no;
    }

    // Método para remover um nó com exatamente um filho
    public void removerComUmFilho(int valor) {
        raiz = removerComUmFilhoRec(raiz, valor); // Chama o método recursivo de remoção
    }

    // Método auxiliar recursivo para remover um nó com um filho
    private No removerComUmFilhoRec(No no, int valor) {
        if (no == null) {
            return null; // Nó não encontrado
        }

        if (valor < no.getValor()) {
            no.setEsq(removerComUmFilhoRec(no.getEsq(), valor)); // Recorre na subárvore esquerda
        } else if (valor > no.getValor()) {
            no.setDir(removerComUmFilhoRec(no.getDir(), valor)); // Recorre na subárvore direita
        } else {
            // Nó encontrado
            if (no.getEsq() == null) {
                return no.getDir(); // Substitui o nó pelo seu filho direito
            } else if (no.getDir() == null) {
                return no.getEsq(); // Substitui o nó pelo seu filho esquerdo
            } else {
                // O nó tem dois filhos, não é o caso aqui
                return no;
            }
        }
        return no;
    }

    // Método para remover um nó com dois filhos
    public void removerComDoisFilhos(int valor) {
        raiz = removerComDoisFilhosRec(raiz, valor); // Chama o método recursivo de remoção
    }

    // Método auxiliar recursivo para remover um nó com dois filhos
    private No removerComDoisFilhosRec(No no, int valor) {
        if (no == null) {
            return null; // Nó não encontrado
        }

        if (valor < no.getValor()) {
            no.setEsq(removerComDoisFilhosRec(no.getEsq(), valor)); // Recorre na subárvore esquerda
        } else if (valor > no.getValor()) {
            no.setDir(removerComDoisFilhosRec(no.getDir(), valor)); // Recorre na subárvore direita
        } else {
            // Nó encontrado
            if (no.getEsq() != null && no.getDir() != null) {
                // O nó tem dois filhos
                No substituto = encontrarMinimo(no.getDir()); // Encontra o nó com o valor mínimo na subárvore direita
                no.setValor(substituto.getValor()); // Substitui o valor do nó pelo valor do nó mínimo
                no.setDir(removerSubstituto(no.getDir(), substituto.getValor())); // Remove o nó mínimo
            }
        }
        return no;
    }

    // Encontra o nó com o valor mínimo na subárvore
    private No encontrarMinimo(No no) {
        while (no.getEsq() != null) {
            no = no.getEsq(); // Move para o nó mais à esquerda
        }
        return no;
    }

    // Método auxiliar recursivo para remover um nó com um valor específico
    private No removerSubstituto(No raiz, int valor) {
        if (raiz == null) {
            return null; // Nó não encontrado
        }

        if (valor < raiz.getValor()) {
            raiz.setEsq(removerSubstituto(raiz.getEsq(), valor)); // Recorre na subárvore esquerda
        } else if (valor > raiz.getValor()) {
            raiz.setDir(removerSubstituto(raiz.getDir(), valor)); // Recorre na subárvore direita
        } else {
            if (raiz.getEsq() == null) {
                return raiz.getDir(); // Substitui o nó pelo seu filho direito
            } else if (raiz.getDir() == null) {
                return raiz.getEsq(); // Substitui o nó pelo seu filho esquerdo
            } else {
                No substituto = encontrarMinimo(raiz.getDir()); // Encontra o nó com o valor mínimo na subárvore direita
                raiz.setValor(substituto.getValor()); // Substitui o valor do nó pelo valor do nó mínimo
                raiz.setDir(removerSubstituto(raiz.getDir(), substituto.getValor())); // Remove o nó mínimo
            }
        }
        return raiz;
    }

    // Método para remover o nó raiz
    public void removerRaiz() {
        if (raiz == null) {
            return; // Árvore está vazia
        }

        if (raiz.getEsq() == null && raiz.getDir() == null) {
            // Caso 1: Raiz é uma folha
            raiz = null; // Remove a folha
        } else if (raiz.getEsq() == null || raiz.getDir() == null) {
            // Caso 2: Raiz tem um filho
            if (raiz.getEsq() != null) {
                raiz = raiz.getEsq(); // Substitui a raiz pelo filho esquerdo
            } else {
                raiz = raiz.getDir(); // Substitui a raiz pelo filho direito
            }
        } else {
            // Caso 3: Raiz tem dois filhos
            No substituto = encontrarMinimo(raiz.getDir()); // Encontra o nó com o valor mínimo na subárvore direita
            raiz.setValor(substituto.getValor()); // Substitui o valor da raiz pelo valor do nó mínimo
            raiz.setDir(removerSubstituto(raiz.getDir(), substituto.getValor())); // Remove o nó mínimo
        }
    }

    public No getRaiz() {
        return this.raiz; // Retorna a raiz da árvore
    }

    // Imprime a árvore na ordem pré-fixada
    public void preOrdem(No no) {
        if (no == null) {
            return;
        }
        System.out.println(no.getValor()); // Imprime o valor do nó
        preOrdem(no.getEsq()); // Recorre na subárvore esquerda
        preOrdem(no.getDir()); // Recorre na subárvore direita
    }

    // Imprime a árvore na ordem infixada
    public void emOrdem(No no) {
        if (no == null) {
            return;
        }
        emOrdem(no.getEsq()); // Recorre na subárvore esquerda
        System.out.println(no.getValor()); // Imprime o valor do nó
        emOrdem(no.getDir()); // Recorre na subárvore direita
    }

    // Imprime a árvore na ordem pós-fixada
    public void posOrdem(No no) {
        if (no == null) {
            return;
        }
        posOrdem(no.getEsq());
        posOrdem(no.getDir());
        System.out.println(no.getValor());
    }
}
