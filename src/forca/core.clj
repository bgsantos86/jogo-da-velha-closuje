(ns forca.core
  (:gen-class))

(def total-de-vidas 6)
(defn perdeu [] (println "Você perdeu!"))
(defn ganhou [] (println "Você ganhou!"))

(defn letras-faltantes [palavra acertos]
  (remove 
    (fn [letra] ; fn cria função sem nome
      (contains? acertos (str letra))) ; [corpo da função]contains verifica se a letra passada tem dentro do conjunto -> acerto
    palavra) ; o remove irá testar cada letra da palavra e verificar se existe correspondência
)
(defn acertou-a-palavra-toda? [palavra acertos] 
  (empty? (letras-faltantes palavra acertos))
)
(defn le-letra! [] (read-line))
(defn acertou? [chute palavra] (.contains palavra chute))

(defn avalia-chute [chute vidas palavra acertos]
  (if (acertou? chute palavra)
    (jogo vidas palavra (conj acertos chute))
    (jogo (dec vidas) palavra acertos)
  )
)
; objetivo: Função principal
; input: números de vidas (int), palavra a ser adivinhada (string), acertos (conjunto de caracteres/string)
; output: nulo
(defn jogo [vidas palavra acertos]
  (if (= vidas 0)   ; Se vidas for igual a zero
    (perdeu)    ; chama a função perdeu
    ; O segundo bloco já é o else.
    (if (acertou-a-palavra-toda? palavra acertos)   ; chama função que verifica se a palavra toda está certa
      (ganhou)
      (avalia-chute (le-letra!) vidas palavra acertos)
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
