# POO Project - Zoo Hotel 

O projecto pode ser **compilado** de duas formas. 
Assumindo que se está no directório que inclui o directório prr (que é o directório raiz que contém o código da aplicação a desenvolver), a compilação pode ser feita das seguintes formas:

`javac -cp po-uilib.jar:. find hva -name "*.java"`

`find hva -name "*.java" -print | xargs javac -cp po-uilib.jar:.`

onde po-uilib.jar é o ficheiro jar com o código da framework de interação com o utilizador e está-se a assumir que também estão no mesmo directório que inclui o directório raiz do projecto. Casos os ficheiros tenham outro nome, ou estejam noutro directório então é necessário alterar o comando por forma a ter em conta as alterações.

Para **executar**:

`java -cp po-uilib.jar:. hva.app.App`


me and @antoniofaia
