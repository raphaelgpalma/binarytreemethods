public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    // Método para inserir valor
    public void inserir(int valor) {
        No novoNo = new No(valor);
        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            No atual = this.raiz;
            No pai = null;
            boolean esquerda = false;
            while (atual != null) {
                if (novoNo.getValor() < atual.getValor()) {
                    pai = atual;
                    atual = atual.getEsq();
                    esquerda = true;
                } else {
                    pai = atual;
                    atual = atual.getDir();
                    esquerda = false;
                }
            }
            if (esquerda) {
                pai.setEsq(novoNo);
            } else {
                pai.setDir(novoNo);
            }
        }
    }

    // Método para remover folha
    public void removerFolha(int valor) {
        raiz = removerFolhaRec(raiz, valor);
    }

    private No removerFolhaRec(No no, int valor) {
        if (no == null) {
            return null; // Valor não encontrado
        }

        if (valor < no.getValor()) {
            no.setEsq(removerFolhaRec(no.getEsq(), valor));
        } else if (valor > no.getValor()) {
            no.setDir(removerFolhaRec(no.getDir(), valor));
        } else {
            // Nó encontrado
            if (no.getEsq() == null && no.getDir() == null) {
                // É uma folha
                return null; // Remove a folha
            } else {
                // Não é uma folha
                return no;
            }
        }
        return no;
    }

    // Método para remover nó com apenas um filho
    public void removerComUmFilho(int valor) {
        raiz = removerComUmFilhoRec(raiz, valor);
    }

    private No removerComUmFilhoRec(No no, int valor) {
        if (no == null) {
            return null; // Nó não encontrado
        }

        if (valor < no.getValor()) {
            no.setEsq(removerComUmFilhoRec(no.getEsq(), valor));
        } else if (valor > no.getValor()) {
            no.setDir(removerComUmFilhoRec(no.getDir(), valor));
        } else {
            // Nó encontrado
            if (no.getEsq() == null) {
                return no.getDir(); // Substitui nó por seu filho direito
            } else if (no.getDir() == null) {
                return no.getEsq(); // Substitui nó por seu filho esquerdo
            } else {
                // O nó tem dois filhos, não é o caso aqui
                return no;
            }
        }
        return no;
    }

    // Método para remover nó com dois filhos
    public void removerComDoisFilhos(int valor) {
        raiz = removerComDoisFilhosRec(raiz, valor);
    }

    private No removerComDoisFilhosRec(No no, int valor) {
        if (no == null) {
            return null; // Nó não encontrado
        }

        if (valor < no.getValor()) {
            no.setEsq(removerComDoisFilhosRec(no.getEsq(), valor));
        } else if (valor > no.getValor()) {
            no.setDir(removerComDoisFilhosRec(no.getDir(), valor));
        } else {
            // Nó encontrado
            if (no.getEsq() != null && no.getDir() != null) {
                // O nó tem dois filhos
                No substituto = encontrarMinimo(no.getDir());
                no.setValor(substituto.getValor());
                no.setDir(removerSubstituto(no.getDir(), substituto.getValor()));
            }
        }
        return no;
    }

    private No encontrarMinimo(No no) {
        while (no.getEsq() != null) {
            no = no.getEsq();
        }
        return no;
    }

    private No removerSubstituto(No raiz, int valor) {
        if (raiz == null) {
            return null;
        }

        if (valor < raiz.getValor()) {
            raiz.setEsq(removerSubstituto(raiz.getEsq(), valor));
        } else if (valor > raiz.getValor()) {
            raiz.setDir(removerSubstituto(raiz.getDir(), valor));
        } else {
            if (raiz.getEsq() == null) {
                return raiz.getDir();
            } else if (raiz.getDir() == null) {
                return raiz.getEsq();
            } else {
                No substituto = encontrarMinimo(raiz.getDir());
                raiz.setValor(substituto.getValor());
                raiz.setDir(removerSubstituto(raiz.getDir(), substituto.getValor()));
            }
        }
        return raiz;
    }

    // Método para remover a raiz
    public void removerRaiz() {
        if (raiz == null) {
            return; // Árvore vazia
        }

        if (raiz.getEsq() == null && raiz.getDir() == null) {
            // Caso 1: Nó raiz é uma folha
            raiz = null;
        } else if (raiz.getEsq() == null || raiz.getDir() == null) {
            // Caso 2: Nó raiz tem um filho
            if (raiz.getEsq() != null) {
                raiz = raiz.getEsq();
            } else {
                raiz = raiz.getDir();
            }
        } else {
            // Caso 3: Nó raiz tem dois filhos
            No substituto = encontrarMinimo(raiz.getDir());
            raiz.setValor(substituto.getValor());
            raiz.setDir(removerSubstituto(raiz.getDir(), substituto.getValor()));
        }
    }

    public No getRaiz() {
        return this.raiz;
    }

    public void preOrdem(No no) {
        if (no == null) {
            return;
        }
        System.out.println(no.getValor());
        preOrdem(no.getEsq());
        preOrdem(no.getDir());
    }

    public void emOrdem(No no) {
        if (no == null) {
            return;
        }
        emOrdem(no.getEsq());
        System.out.println(no.getValor());
        emOrdem(no.getDir());
    }

    public void posOrdem(No no) {
        if (no == null) {
            return;
        }
        posOrdem(no.getEsq());
        posOrdem(no.getDir());
        System.out.println(no.getValor());
    }
}
