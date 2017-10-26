//main file for wordfreqs

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char *argv[]){
	const char accept[100] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	char file[100];
	int **hash_table;
	strcpy(file, argv[1]);
	const char delim[20] = " \n,-.:;";
	printf("file name is %s", file);
	FILE *fp = fopen(file, "r");
	if(fp==NULL){//out of range query probably
		printf("Can't open file\n");
		return;
	}
	fseek(fp, 0L, SEEK_END);
	int f_size = ftell(fp);
	fseek(fp, 0L, SEEK_SET);
	char *text = (char *) malloc(f_size);
	char str[100];
	//Calculates the length of the initial segment of str1 which consists entirely of characters in str2.
	//size_t strspn(const char *str1, const char *str2);
	while(fgets(str, f_size, fp) != NULL){
		printf("%s\n", text);
		strcat(text, str);
	}
	char *token;
	token = strtok(text, delim);
	while(token != NULL){
		printf("%s\n", token);
		token = strtok(NULL, delim);
	}

}

//This function removes the apostrophes in a line
void apos_proc(char *str){
	int len = strlen(str);
	for(i = 0; i < strlen(str); i++){
		
	}
}