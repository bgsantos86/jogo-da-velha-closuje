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

(defn imprime-forca [vidas palavra acertos]
  (println "Vidas " vidas)
  (doseq [letra (seq palavra)] 
    (if (contains? acertos (str letra))
      (print letra " ") 
      (print "_" " ")
    )
  )
  (println)
)

; objetivo: Função principal
; input: números de vidas (int), palavra a ser adivinhada (string), acertos (conjunto de caracteres/string)
; output: nulo
(defn jogo [vidas palavra acertos]
  (imprime-forca vidas palavra acertos)
  (cond 
    (= vidas 0) (perdeu)    ; Se vidas for igual a zero / chama a função perdeu
    (acertou-a-palavra-toda? palavra acertos) (ganhou) ; chama função que verifica se a palavra toda está certa
    :else
    (let [chute (le-letra!)]
      (if (acertou? chute palavra)
        (do
          (println "Você acertou!")
          (recur vidas palavra (conj acertos chute))
        )
        (do
          (println "Você errou!")
          (recur (dec vidas) palavra acertos)
        )
      )
    )
  )
)

(defn comeca-o-jogo [] (jogo total-de-vidas "banana" #{}))

(defn -main
  [& args]
  (comeca-o-jogo))
