//@author Ian White
//@date 10/9/17
//This program counts the words in a text file using a hash table. 
//
console.log("enter a text file for counting:");

process.argv.forEach(function (val, index, array) {
	console.log(index + ': ' + val);
});