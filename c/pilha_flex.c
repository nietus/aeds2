#include<stdio.h>
#include<stdlib.h>
#include<string.h>

struct celula{
    int elemento;
    struct celula *prox;
};

struct celula* new_celula(int elemento){
    struct celula *tmp = (struct celula*)malloc(sizeof(struct celula));
    tmp->elemento = elemento;
    tmp->prox = NULL;
    return tmp;
}

struct celula *topo;

void start(){
    topo = NULL;
}

void empilhar(int x){
    struct celula *tmp = new_celula(x);
    tmp->prox = topo;
    topo = tmp;
    tmp = NULL;
}

int desempilhar(){
    if(topo == NULL){
        printf("Pilha ja esta vazia");
    }
    int resp = topo->elemento;
    struct celula *tmp = topo;
    topo = topo->prox;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}