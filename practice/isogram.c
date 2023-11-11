#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

bool IsIsogram(const char *string);

int main() {
    const char *testString = "aA";
    bool result = IsIsogram(testString);

    if (result) {
        printf("'%s' is an isogram.\n", testString);
    } else {
        printf("'%s' is not an isogram.\n", testString);
    }

    return 0;
}
bool IsIsogram (const char *string) 
{
  int n[128];
  for(int i = 0; i < 128; i++){
    n[i] = 0;
  }
  for(int i = 0; i < (int) strlen(string);i++){
    char c = tolower(string[i]);
    int value = (int) c;
    n[value] += 1;
    if(n[value] > 1){
    return false;
    }
  }
	return true;
}