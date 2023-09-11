#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *arquivo;
    int n;
    scanf("%d", &n);

    // Abertura do arquivo para escrita
    arquivo = fopen("valores.txt", "wb");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo para escrita.\n");
        return 1;
    }

    // Leitura dos valores reais e escrita no arquivo
    double valor;
    for (int i = 0; i < n; i++) {
        scanf("%lf", &valor);
        fwrite(&valor, sizeof(double), 1, arquivo);
    }

    // Fechar o arquivo
    fclose(arquivo);

    // Reabrir o arquivo para leitura de trás para frente
    arquivo = fopen("valores.txt", "rb");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo para leitura.\n");
        return 1;
    }

    // Ler e mostrar os valores de trás para frente
    for (int i = n - 1; i >= 0; i--) {
        fseek(arquivo, i * sizeof(double), SEEK_SET);
        fread(&valor, sizeof(double), 1, arquivo);
        if (valor == (int)valor) {
            printf("%d\n", (int)valor); // Imprimir como inteiro
        } else {
            printf("%.3lf\n", valor); // Caso contrário, imprimir com a parte decimal
        }
    }

    // Fechar o arquivo novamente
    fclose(arquivo);

    return 0;
}
