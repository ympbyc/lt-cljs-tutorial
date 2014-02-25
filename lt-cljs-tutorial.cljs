;; +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
;; An Introduction to ClojureScript for Light Table users
;; Light TableユーザーのためのClojureScript紹介
;; +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


;; Basics
;; 始めに
;; ============================================================================

;; To begin, open the command pane (type Control-SPACE), Add Connection, select
;; Light Table UI. Once connected you can evaluate all the forms in this file
;; by placing the cursor after the form and typing Command-ENTER.

;j; 始る前、Command Paneを(Contol-SPACEで)開いて、「Add Connection」を検索して、選択してください。
;j; そして、出てくるリストから「Light Table UI」を選択してください。接続できたら、このファイルの中の
;j; ステートメントをカーサー持ってきて、「Command-Enter」を押すと、評価が出来ます。

;; IMPORTANT: You must evaluate the very first form, the namespace
;; definition.

;j; 注意：一番始めのnamespaceステートメントを評価する必要があります。

;; Declaring a namespaces
;; ----------------------------------------------------------------------------

;j; Namespaceの定義
;j; ----------------------------------------------------------------------------

;; ClojureScript supports modularity via namespaces. They allow you to group
;; logical definitions together.

;j; ClojureScriptのモジュラリティがnamespace(ネームスペーす)で提供しています。

(ns lt-cljs-tutorial
  (:require [clojure.string :as string]))

;; :require is how you can import functionality from a different namespace into
;; the current one. Here we are requiring `clojure.string` and giving it an
;; alias. We could write the following:

;j; :requireを利用して、他のnamespaceからの機能を現在のnamespaceにインポートすることができます。
;j; 上記のように`clojure.string`を:requireして、stringと言うエイリアスを付ける事が出来ます。

(clojure.string/blank? "")

;; But that's really verbose compared to:

;j; そして、上記のちょっと冗長ば使い方が下記の書き方になります。

(string/blank? "")


;; Comments
;; ----------------------------------------------------------------------------

;j; コメント
;j; ----------------------------------------------------------------------------

;; There are three ways to create comments in ClojureScript. The first way is
;; by preceding a line with a semi-colon, just like the lines you are reading
;; now.

;j; ClojureScriptでは、コメントを作る方法3つあります。まず一つ目は、今読んでいる
;j; 文書のように、ラインの頭に「;」(セミコロン)を付ける事だけでコメントになります。

;; The second way is by preceding a form with `#_`. This causes ClojureScript
;; to skip the evaluation of only the form immediately following, without
;; affecting the evaluation of the surrounding forms.

;j; 2つ目の方法は、ステイトメントのあたまに「#_」を付ます。そうしますと、ClojureScript
;j; が付けられたステイトメントの評価を飛ばします。その他、周りのステイトメントの評価には、
;j; 影響がありません。

;; Try to reveal the secret message below:

;j; 下記の秘密のメッセージを解けます？

(str "The secret word is " #_(string/reverse "tpircSerujolC"))

;; Finally, you can also create a comment using the `comment` macro. One common
;; technique is to use the `comment` macro to include code to be evaluated in a
;; REPL, but which you do not normally want to be included in the compiled
;; source.

;j; 最後のコメントを作る方法は`comment`のマクロを利用する事です。このマクロを使った、ちょっと便利なパターン
;j; の一つは、REPLで使いたいのみのコードを`comment`マクロで巻いて、コンパイルしたらそのコードは含めません。

;; For example, try placing your cursor after the last `)` below and type
;; Command-ENTER:

;j; 例えば、下記のステイトメントの最後の「)」にカーソルを持ってきて、Command-ENTERで評価してみましょう。

(comment

  (string/upper-case "This is only a test...")

  )

;; The `comment` macro makes the whole form return `nil`. Now go back and
;; highlight just the middle line, then type Command-ENTER. In this way
;; you can include code samples or quick tests in-line with the rest of
;; your code.

;j; Commentマクロが、ステイトメントの結果を「nil」にします。そして次は、真ん中の
;j; ステイトメントを選択してCommand-ENTERをやってみてください。
;j; このように、単純なサンプルやテストを他のコードの間に挟む事が出来ます。

;; Definitions
;; ----------------------------------------------------------------------------

;j; 定義
;j; ----------------------------------------------------------------------------

;; Once you have a namespace, you can start creating top level definitions in
;; that namespace.

;; namespaceが出来たので、namespaceの中のトップレベル定義を作って見ましょう。

;; You can define a top level with `def`.

;j; トップレベルの定義は`def`で行います。


(def x 1)

x

;; You can also refer to top level definitions by fully qualifying them.

;j; namespaceの名前をつかって、トップレベルの定義を直接アクセス出来ます。

lt-cljs-tutorial/x

;; This means top levels can never be shadowed by locals and function
;; parameters.

;j; ネームスペースを利用すると、トップレベルの関数はローカル関数やファンクションでかぶる事がないです。

(let [x 2]
  lt-cljs-tutorial/x)

;; One way to define a function is like this.

;j; ファンクションを定義する方法の一つ：

(def y (fn [] 1))

(y)

;; Defining functions in ClojureScript is common enough that `defn` sugar is
;; provided and idiomatic.

;j; ファンクションを作るのはよくある事ので、`defn`と言うシュガーがあって、利用するのが慣用的です。

(defn z [] 1)

(z)


;; Literal data types
;; ----------------------------------------------------------------------------

;j; データタイプのリテラル
;j; ----------------------------------------------------------------------------

;; ClojureScript comes out of the box with the usual useful data literals.

;j; ClojureScriptが便利なデータリテラルをたくさん含まれています。

;; Booleans

;j; ブーリアン

(def a-boolean true)

;; Strings

;j; ストリング

(def a-string "Hello!")

;; Regular Expressions

;j; レギュラー・エクスプレション

(def a-regexp #"\d{3}-?\d{3}-?\d{4}")

;; Numbers

;j; 数字

(def a-number 1)


;; Function literals
;; ----------------------------------------------------------------------------

;j; ファンクションリテラル
;j; ----------------------------------------------------------------------------

;; ClojureScript also supports a shorthand function literal which is useful
;; You can use the % and %N placeholders to represent function arguments.

;j; ClojureScriptはファンクションリテラルを作るには、便利な速記があります。
;j; そして、その中、ファンクションの引数の代わりに、「%」から「%N」のプレイスホルダーがあります。

;; You should not abuse the function literal notation as it degrades readability
;; outside of simple cases. It is nice for simple functional cases such as
;; the following. You could map over a ClojureScript vector like this:

;j; ファンクションリテラルの悪用を気をつけましょう。シンプルのケース意外使いすぎちゃうと、
;j; とても読みにくくなります。でも下記のようなシンプルのケースのためには、便利です。
;j; ClojureScriptのベクトルを下記のようにmapかけることができます。

(map (fn [n] (* n 2)) [1 2 3 4 5])

;; Or you can save typing a few characters like this:
;j; そして、ちょっと入力の手間を省くことができます。

(map #(* % 2) [1 2 3 4 5])


;; JavaScript data type literals
;; ----------------------------------------------------------------------------

;j; JavaScriptのデータタイプのリテラル
;j; ----------------------------------------------------------------------------

;j; You can construct a JavaScript array with the `array` function.
;j; JavaScriptの配列を`array`ファンクションを利用して作れます。

(def an-array (array 1 2 3))

;; But ClojureScript also supports JavaScript data literals via the `#js`
;; reader literal.

;j; それとも、ClojureScriptが`#js`と書くリーダーリテラルでもJavaScriptのデータリレラルを
;j; サポートしています。

(def another-array #js [1 2 3])

;; Similarly, you can create simple JavaScript objects with `js-obj`.

;j; 同じように、JavaScriptのオブジェクトを`js-obj`で作れます。

(def an-object (js-obj "foo" "bar"))

;; But again you can save a few characters with `#js`.

;j; ちょっと手間を省いて、`#js`を使った場合。

(def another-object #js {"foo" "bar"})

;; It's important to note that `#js` is shallow, the contents of `#js` will be
;; ClojureScript data unless preceded by `#js`.

;j; 注意しないと行けないのは、`#js`の効力が浅いです。`#js` の中のステイトメントが`#js` を
;j; 付けないと、ClojureScriptになります。

;; This is a mutable JavaScript object with an immutable ClojureScript vector
;; inside.

;j; 下記では、ミュータブルなJavaScriptオブジェクトの中にイミュータブルClojureScriptベクトル
;j; が入っています。

(def shallow #js {"foo" [1 2 3]})


;; Constructing a type
;; ----------------------------------------------------------------------------

;j; タイプの構築
;j; ----------------------------------------------------------------------------

;; Of course some JavaScript data types you will want to create with a
;; constructor.

;j; もちろん、コンストラクターで作りたいJavaScriptのデータタイプも出てくるでしょう。

;; (js/Date.) is equivalent to new Date().

;; (js/Date.)が Date()の同等となります。

(def a-date (js/Date.))

(def another-date #inst "2014-01-15")

;; Note the above returns an `#inst` data literal.

;j; 上記が、`#inst`のデータリテラルを戻すのを注目してください。

(def another-regexp (js/RegExp. "\\d{3}-?\\d{3}-?\\d{4}"))

;; Handy

;j; 便利ですね！

;; NOTE: js/Foo is how you refer to global JavaScript entities of any kind.

;j; 注意： どのグロバルのJavaScriptを、js/Fooで参照します。

js/Date

js/RegExp

js/requestAnimationFrame

;; If you're curious about other JavaScript interop jump to the bottom of this
;; tutorial.

;j; ほかのJavaScriptとの相互運用に興味があるなら、このドキュメントの真下に飛んでください。


;; ClojureScript data types
;; ============================================================================

;; Unless there is a good reason, you should generally write your ClojureScript
;; programs with ClojureScript data types. They have many advantages over
;; JavaScript data types - they present a uniform API and they are immutable.

;j; ClojureScriptのデータタイプ
;j; ============================================================================


;; Vectors
;; ----------------------------------------------------------------------------

;j; ベクトル
;j; ----------------------------------------------------------------------------

;; Instead of arrays, ClojureScript programmers use persistent vectors. They are
;; like arrays - they support efficient random access, efficient update
;; and efficient addition to the end.

;j; 配列の代わりに、ClojureScriptのプログラマーは永続的なベクトルを使います。配列のように、
;j; ランダムのアクセスや、アップデートと終わりにエレメントを足すことを効率的できます。


(def a-vector [1 2 3 4 5])

;; We can get the length of a vector in constant time via `count`.

;j; ベクトールの長さをコンスタントタイムで調べられます。

(count a-vector)

;; We can add an element to the end.

;j; そして、最後にエレメントを足せます。

(def another-vector (conj a-vector 6))

;; Note this does not mutate the array! `a-vector` will be left
;; unchanged.

;j; これがベクトールを突然変異しないことに注目！`a-vector`に変化がない。

a-vector

another-vector

;; Hallelujah! Here is where some ClojureScript magic
;; happens. `another-vector` appears to be a completely new vector
;; compared to `a-vector`. But it is not really so. Internally, the new
;; vector efficiently shares the `a-vector` structure. In this way, you
;; get the benefits of immutability without paying in performance.

;j; Hallelujah!ClojureScriptのマジックはこういう事から生まれてきます。`another-vector`
;j; が、`a-vector`に対して全く新しいベクトルに見えます。でも、そうでもないです。内部では、
;j; 新しいベクトルが`a-vector` の構造をシェアしています。このように、不変性の効果が
;j; パフォーマンスに悪影響なく使えます。

;; We can access any element in a vector with `nth`. The following
;; will return the second element.

;j; ベクトルのエレメントどれでも`nth`でアクセスできる。下記が、2番目のエレメントを
;j; 返してくれます。

(nth a-vector 1)

(nth ["foo" "bar" "baz"] 1)

;; Or with `get`...

;j; そして、`get`...

(get a-vector 0)

;; ...which allows you to return an alternate value when the index is
;; out-of bounds.

;; .. 範囲外のインデクスを使った場合、別の結果を返せる事ができます。

(get a-vector -1 :out-of-bounds)
(get a-vector (count a-vector) :out-of-bounds)

;; Surprisingly, vectors can be treated as functions. This is actually
;; a very useful property for associative data structures to have as
;; we'll see below with sets.

;j; 予想外な事で、ベクトルがファンクションの用に使えます。実は、これが連想データ構造
;j; にとってとっても便利な特徴です。下記、セットを使ったときに明らかになると思います。

(a-vector 1)

(["foo" "bar" "baz"] 1)


;; Maps
;; ----------------------------------------------------------------------------

;j; マップ
;j; ----------------------------------------------------------------------------

;; Along with vectors, maps are the most common data type in ClojureScript.
;; Map usage is analogous to the usage of Object in JavaScript, but
;; ClojureScript maps are immutable and considerably more flexible.

;j; ClojureScriptではベクトルの次は、マップが一番よく見かけるデータタイプです。
;j; ClojureScriptのマップはJavaScriptのObjectと同質的な役割しています。
;j; でも、ClojureScriptのマップはイミュータブルですし、かなり柔軟で使えます。


;; Let's define a simple map. Note `:foo` is a ClojureScript keyword.
;; ClojureScript programmers prefer to use keywords for keys instead
;; of strings. They are more distinguishable from the rest of the
;; code, more efficient than plain strings, and they can be used in
;; function position (i.e. first position after the open parens), as
;; we'll see in a moment.

;j; まず、単純なマップを作りましょう。ちなみに、`:foo`がClojureScriptのキーワードです。
;j; マップにキーには、ストリングよりキーワードを使う方がClojureScriptプログラマーが好きです。
;j; ほかのコード区別しやすいし、ストリングよりは効率的ですし、そして、下記で説明するように
;j; ファンクションのポジション(ブラケットのすぐ後)にでも使えますです。

(def a-map {:foo "bar" :baz "woz"})

;; We can get the number of key-value pairs in constant time.

;j; キーバルユーペアを一定時間で調べられます。

(count a-map)

;; We can access a particular value for a key with `get`.

;j; そして、気になるキーのバリューを`get`でアクセスできる。

(get a-map :foo)

;; and return an alternative value when the key is not present

;j; そして、存在してないキーの代わりになるバリューも返せます。

(get a-map :bar :not-found)

;; We can add a new key-value pair with `assoc`.

;j; 新しいキーバリューペアを`assoc`で足せます。

(def another-map (assoc a-map :noz "goz"))

;; Again a-map is unchanged! Same magic as before for vectors

;j; またa-mapは変わってない！ベクトルで見た、同じマジックです。

a-map

another-map

;; We can remove a key-value pair with `dissoc`.

;j; `dissoc`でキーバリューペアを削除できます。

(dissoc a-map :foo)

;; Again a-map is unchanged!

;j; またまた、a-mapが変わってないです。

a-map

;; Like vectors, maps can act like functions.

;j; ベクトルのと同じように、マップがファンクション野代わりに使えます。

(a-map :foo)

;; However ClojureScript keywords themselves can act like functions and the
;; following is more idiomatic.

;j; ただ、ClojureScriptでは、キーワードもファンクション野用に使えます。こっちの方が慣用的です。

(:foo a-map)

;; We can check if a map contains a key, with `contains?`.

;j; マップであるキーが存在しているかどうかを、`contains?`で確認できます。

(contains? a-map :foo)

;; We can get all the keys in a map with `keys`.

;j; マップのすべてのキーを`keys`で調べられます。

(keys a-map)

;; And all of the values with `vals`.

;j; そして、すべてのバリューを`vals`で。

(vals a-map)

;; We can put a lot of things in a map, even other maps

;j; マップには、いろんな種類のバリューをインサートできる。マップでも。

(def a-nested-map {:customer-id 1e6
                   :preferences {:nickname "Bob"
                                 :avatar "http://en.gravatar.com/userimage/0/0.jpg"}
                   :services {:alerts {:daily true}}})

;; and navigate its keys to get the nested value you're interested in

;j; そして、キーを操縦して、入れ子の気になるバリュー調べたり、

(get-in a-nested-map [:preferences :nickname])
(get-in a-nested-map [:services :alerts :daily])

;; or just find a top level key-value pair (i.e. MapEntry) by key

;j; トップレベルのキーバリューペアを探せます。

(find a-nested-map :customer-id)
(find a-nested-map :services)

;; There are many cool ways to create maps.

;j; マップを作るには、下記のようないろんな方法があります。

(zipmap [:foo :bar :baz] [1 2 3])

(hash-map :foo 1 :bar 2 :baz 3)

(apply hash-map [:foo 1 :bar 2 :baz 3])

(into {} [[:foo 1] [:bar 2] [:baz 3]])

;; Unlike JavaScript objects, ClojureScript maps support complex keys.

;j; JavaScriptのオブジェクトと違って、ClojurScriptのマップは複雑なキーを利用できます。

(def complex-map {[1 2] :one-two [3 4] :three-four})

(get complex-map [3 4])


;; Keyword digression
;; ----------------------------------------------------------------------------

;j; キーワード余談
;j; ----------------------------------------------------------------------------

;; Let's take a moment to digress about keywords as they are so ubiquitous
;; in ClojureScript code.

;j; ClojureScriptでは、キーワードが偏在するので、いったん話を脱線して、調べましょう。

(identity :foo)

;j; If you add an additional preceding colon you'll get a namespaced keyword.
;j; もう一つのコロンを付け足すと、ネームスペース化されたキーワードを作れます。

(identity ::foo)

;; What good is this for? It allows you to put data into collections without
;; fear of namespace clashes without the tedium of manual namespacing them
;; in your source.

;j; これは、何の訳にたつ？退屈なネームスパースをマニュアル付け足す作業を避けて、ネームスペースの中のデータを
;j; 絶対にかぶらない用にデータをコレックションに付け足せる事ができます。

(identity {:user/foo ::foo})

;; Namespaced keywords are essential to Light Table's modularity.

;j; ネームスペース化されたケーワードが、Light Tableのモジュール化には、大切な仕様です。


;; Sets
;; ----------------------------------------------------------------------------

;j; セット
;j; ----------------------------------------------------------------------------

;; ClojureScript also supports sets.

;; ClojureScriptはセットも対応しています。

(def a-set #{:cat :dog :bird})

;; `:cat` is already in `a-set`, so it will be unchanged.

;j; `:cat`がすでに`a-set`に含まれているので代わりがないです。

(conj a-set :cat)

;; But `:zebra` isn't.

;j; しかし、`:zebra`はそうでもない。

(conj a-set :zebra)

;; If you haven't guessed already, `conj` is a "polymorphic" function that adds
;; an item to a collection. This is some of the uniformity we alluded to
;; earlier.

;j; 気づきましたか？`conj`が「ポリモーフィック」な、コレクションに何かを足すために使うファンクションです。
;j; これが前少し言及した均一性です。

;; `contains?` works on sets just like it does on maps.

;j; `contains?` も、マップと同じように、セットにも使えます。

(contains? a-set :cat)

;; Like vectors and maps, sets can also act as functions. If the argument
;; exists in the set it will be returned, otherwise the set will return nil.

;j; また、ベクトリとマップと同じく、セットがファンクションの用には使えます。引数がセットにあれば、
;j; そのままかえってきます。そうではないなら、`nil`が戻ります。

(#{:cat :dog :bird} :cat)

(#{:cat :dog :bird} :banana)

;; This is powerful when combined with conditionals.

;j; if文と組み合わせると、パワフルな仕組みになります。

(defn check [x]
  (if (#{:cat :dog :bird} x)
    :valid
    :invalid))

(check :cat)
(check :zebra)


;; Lists
;; ----------------------------------------------------------------------------

;j; リスト
;j; ----------------------------------------------------------------------------

;; A less common ClojureScript data structure is lists. This may be
;; surprising as ClojureScript is a Lisp, but maps, vectors and sets
;; are the 'go-to' data structures for most applications. Still, lists are sometimes
;; useful—especially when dealing with code (i.e. code is data).

;j; リストはClojureScriptでの、もう少し珍しいデータ構造です。ClojureScriptがLispである事によって、
;j; これがちょっとびっくりな事かもしれないですが、マップ、ベクトルとセットが手をよくつけるデータ構造です。
;j; しかし、リストが便利なときもあります。特に、コードを対応している時に(コードをデータとして)使います。

(def a-list '(:foo :bar :baz))

;; `conj` is "polymorphic" on lists as well, and it's smart enough to
;; add the new item in the most efficient way on the basis of the
;; collection type.

;j; ポリモーフィックファンクション`conj`がリストにも対応しています。そして、コレクションおタイプによって、
;j; 一番効率的な足し方をしてくれます。賢いですね！

(conj a-list :front)

;; and lists are immutable as well

;j; リストもイミュータブルです。

a-list

;; You can get the first element of a list

;j; リストの頭にあるエレメントを読めます。

(first a-list)

;; or the tail of a list

;j; それとも、しっぽ。

(rest a-list)

;; which allows you to easly verify how ClojureScript shares data
;; structure instead of inefficiently copying data for supporting
;; immutability.

;j; そして、これを利用して、ClojureScriptのイミュータビリティーが、
;j; 非効率的にデータのコピーではなくて、データの構造を共有でできているのを簡単に確認できます。

(def another-list (conj a-list :front))

another-list

a-list

(identical? (rest another-list) a-list)

;; `identical?` checks whether two things are represented by the same
;; thing in memory.

;j; `identical?`が、二つの物がメモリー上で同じ表現しているかどうかを確認します。

;; Equality
;; ============================================================================

;j; 平等
;j; ============================================================================

;; ClojureScript has a much simpler notion of equality than what is present
;; in JavaScript. In ClojureScript equality is always deep equality.

;j; ClojureScriptでは、なにが平等しているという概念がJavaScriptより単純です。
;j; ClojureScriptでは、深い平等が標準です。

(= {:foo "bar" :baz "woz"} {:foo "bar" :baz "woz"})

;; Maps are not ordered.

;j; マップには、順序付けられてない。

(= {:foo "bar" :baz "woz"} {:baz "woz" :foo "bar"})

;; For sequential collections, equality just works.

;j; 逐次的なコレックションにでも平等が普通に動きます。

(= [1 2 3] '(1 2 3))

;; Again, it is possible to check whether two things are represented
;; by the same thing in memory with `identical?`.

;j; そして、二つの物がメモリー上で同じ表現しているかどうかを確認するには、`identical?`があります。

(def my-vec [1 2 3])
(def your-vec [1 2 3])
(def my-vec2 my-vec)

(identical? my-vec your-vec)
(identical? my-vec my-vec2)


;; Control
;; ============================================================================

;; In order to write useful programs, we need to be able to express
;; control flow. ClojureScript provides the usual control constructs,
;; however truth-y and false-y values are not the same as in
;; JavaScript so it's worth reviewing.

;j; コントロール
;j; ============================================================================

;j; 使えるプログラムを作るには、コントロールフローを表現しなければならない。
;j; ClojureScriptがコントロール構造はありますが、truthyとfalsyのバリューがJavaScript
;j; と違いがありますので、見直しましょう。


;; if
;; ----------------------------------------------------------------------------

;; 0 is not a false-y value.

;j; 0がfalse-yではない。

(if 0
  "Zero is not false-y"
  "Yuck")

;; Nor is the empty string.

;j; 空のストリングでも、

(if ""
  "An empty string is not false-y"
  "Yuck")

;; the empty vector

;j; 空のベクトルでも、

(if []
  "An empty vector is not false-y"
  "Yuck")

;; the empty list

;j; 空のリストでも、

(if ()
  "An empty list is not false-y"
  "Yuck")

;; the empty map

;j; 空のマップでも、

(if {}
  "An empty map is not false-y"
  "Yuck")

;; the empty set

;j; 空のセットでも、

(if #{}
  "An empty set is not false-y"
  "Yuck")

;; and even the empty regexp

;j; 空のレギュラーエクスプレションでもfalse-yではない！

(if #""
  "An empty regexp is not false-y"
  "Yuck")

;; The only false-y values in ClojureScript are `nil` and `false`. `undefined`
;; is not really a valid ClojureScript value and is generally coerced to `nil`.

;j; ClojureScriptではfalse-yのバリューが、`nil` と `false`の二つだけです。
;j; `undefined`がClojureScriptでは有効なではないので、普段は`nil`に強制されています。


;; cond
;; ----------------------------------------------------------------------------

;; Nesting `if` tends to be noisy and hard to read so ClojureScript
;; provides a `cond` macro to deal with this.

;j; たくさんな`if`が重ねてくるととてもうるさくなりますので、Clojurescriptが
;j; このために`cond`のマクロを提供しています。

(cond
  nil "Not going to return this"
  false "Nope not going to return this either"
  :else "Default case")


;; loop/recur
;; ----------------------------------------------------------------------------

;; The most primitive looping construct in ClojureScript is `loop`/`recur`.
;; Like `let`, `loop` establishes bindings and allows you to set their initial values.
;; Like `let`, you may have a sequence of forms for the body. In tail
;; positions, you may write a `recur` statement that will set the bindings for
;; the next iteration of the `loop`. Using `loop`/`recur` is usually considered bad
;; style if a reasonable functional solution via `map`/`filter`/`reduce` or a list
;; comprehension is possible.

;j; ClojureScriptの最もプリミティブなループ構造が`loop`/`recur`です。
;j; `let`見たいに、`loop` では、バインディングを確立できます。そして初期バリューも設定できます。
;j; また、`let`と同じく、ステートメントのシークエンスがボディーとなります。しっぽのポジションだけで、
;j; 次の反復野バインディングを設定している`recur`ステートメントを書く事ができます。
;j; しかし、`map`/`filter`/`reduce`やリストの内包表記(List Comprehensionなどを使った、
;j; ファンクショナルな解決方法があるとき、`loop`/`recur` を使うのが非慣用と思われています。


;; While you might write this in JavaScript:

;j; この風に書くJavaScriptを:

;; var ret = [];
;; for(var i = 0; i < 10; i++) ret.push(i)

;; In ClojureScript you would write `loop`/`recur` like so:

;j; ClojureScriptではこの風に`loop`/`recur`でかきます。

(loop [i 0 ret []]
  (if (< i 10)
    (recur (inc i) (conj ret i))
    ret))

;; Again avoid `loop`/`recur` unless you really need it. The loop above would
;; be better expressed as the following:

;j; しかし、本当に必要ではないなら、`loop`/`recur`を使用しないように書いた方がいいです。
;j; 上記のループが下記の用に書いた方が正しいです。

(into [] (range 10))


;; Moar functions
;; ============================================================================

;j; functionsをもっと！
;j; ============================================================================

;; Functions are the essence of any significant ClojureScript program, so
;; we will dive into features that are unique to ClojureScript functions that
;; might be unfamiliar.

;j; ClojureScriptのプログラムの本質はファンクションであるので、ClojureScriptのファンクションで
;j; もしかしての見知らぬ部分をちょっと深く探りましょう。

;; Here is a simple function that takes two arguments and adds them.

;j; 下記のファンクションが、二つの引数うを受け取って、足します。

(defn foo1 [a b]
  (+ a b))

(foo1 1 2)

;; Functions can have multiple arities.

;j; ファンクションは複数のアリティを持つ事で着ます。

(defn foo2
  ([a b] (+ a b))
  ([a b c] (* a b c)))

(foo2 3 4)
(foo2 3 4 5)

;; Multiple arities can be used to supply default values.

;j; 複数のアリティを利用する事で、デフォルトのバリューを給与できます。

(defn defaults
  ([x] (defaults x :default))
  ([x y] [x y]))

(defaults :explicit)
(defaults :explicit1 :explicit2)

;; Functions support rest arguments.

;j; 残りの複数を受け取る「レスト複数」も、作れます。

(defn foo3 [a b & d]
  [a b d])

(foo3 1 2)
(foo3 1 2 3 4)

;; You can apply functions.

;j; ファンクションをapplyで付ける事ができます。

(apply + [1 2 3 4 5])


;; multimethods
;; ----------------------------------------------------------------------------

;j; マルチーメソッド
;j; ----------------------------------------------------------------------------

;; Often when you need some polymorphism, and performance isn't an issue,
;; multimethods will suffice. Multimethods are functions that allow open
;; extension, but instead of limiting dispatch to type, dispatch is controlled
;; by whatever value the dispatch fn originally supplied to `defmulti` returns.

;j; よくポリモーフィズムが必要となって、そしてパーフォーマンスが大事ではない時、
;j; マルチメソッドを利用する事が十分です。マルチメソッドは、自由に拡張できるファンクション、
;j; しかし、タイプでディスパッチする事に限っているではなくて、ディスパッチが
;j; `defmulti`に最初に渡されたファンクションの返すバリューによってコントロールされています。

;; Here is the simplest multimethod you can write. It simply dispatches on
;; the value received.

;j; 例えば、下記が最も単純なマルチメソッドです。受け取るバリューだけでディスパッチするだけです。

(defmulti simple-multi identity)

;; Now we can define methods for particular values.

;j; そしたら、特定したバリューのメソッドを定義できます。

(defmethod simple-multi 1
  [value] "Dispatched on 1")

(simple-multi 1)

(defmethod simple-multi "foo"
  [value] "Dispatched on foo")

(simple-multi "foo")

;; However we haven't defined a case for "bar"

;j; まだ、"bar"の場合を定義してないのに：

;; (Highlight  the `simple-multi` form below)

;j; (下記の`simple-multi`をハイライトして評価して見てください。

(comment
  (simple-multi "bar")
  )

;; Here is a function that takes a list. It dispatches on the first element
;; of the list!

;j; そして、下記はリストを受け取って、リストの最初のエレメントでディスパッチする
;j; ファンクションです。

(defmulti parse (fn [[f & r :as form]] f))

(defmethod parse 'if
  [form] {:op :if})

(defmethod parse 'let
  [form] {:op :let})

(parse '(if a b c))
(parse '(let [x 1] x))


;; Scoping
;; ============================================================================

;j; スコープ
;j; ============================================================================

;; Unlike JavaScript, there is no hoisting in ClojureScript. ClojureScript
;; has lexical scoping.
;;
;j; ClojureScriptはJavaScriptと違って、変数の巻き上げはありません。
;j; ClojureScriptのスコープは、静的スコープです。

(def some-x 1)

(let [some-x 2]
  some-x)

some-x

;; Closures
;; ----------------------------------------------------------------------------
;;
;j; クロージャ
;j; ----------------------------------------------------------------------------

;; Could a language with such a name miss closures? Surely it can't. You
;; may be already familiar with them in JavaScript, even if it's a
;; variable scoped language.

;j; この名前を与えた言語は「クロージャ」がないと思いますか？もちろん、あり得ないです。
;j; バリアブルスコープの言語であるJavaScriptからでも、見た事あるでしょうか。


(let [a 1e3]
(println (type a))
  (defn foo []
    (* a a))
  (defn bar []
    (+ (foo) a)))

;; Above we defined `foo` and `bar` functions inside the scope of a
;; `let` form and they both know about `a` (i.e. they close over `a`).
;; Note, even if defined inside a `let`, `foo` and `bar` are available
;; in the outer scope. This is because all `def` expressions are always
;; top level. See the footnote at the end of this section.

;j; 上記で、`foo`と`bar`と言うファンクションを`let`形式の中で定義しました。どちらも、
;j; `a`の存在を意識しています。(とはいえ、`a`を含んでいる)。`let`の中で定義されていても、
;j;  `foo`と`bar`が外のスコープからでもアクセスできます。詳しくは、下記の脚注を確認してください。


(foo)
(bar)

;; And Nobody else.

;j; 別のスコープからは、アクセスできない。

(comment
  (defn baz []
    (println (type a)))
  (baz)
  )

;; That's why some people say that closures are the poor man's objects.
;; They encapsulate the information as well.

;j; 「クロージャは貧乏人のオブジェクト」というフレーズはこういう、情報を含まれること
;j; から生まれたフレーズでしょう。

;; But in ClojureScript, functions' parameters and let bindings' locals
;; are not mutable! That goes for loop locals, too!

;j; ClojureScriptでは、ファンションの引数と`let`のローカルが、ミュータブルではない！
;j; ループのローカルもそうです。

(let [fns (loop [i 0 ret []]
            (if (< i 10)
              (recur (inc i) (conj ret (fn [] i)))
              ret))]
  (map #(%) fns))

;; In JavaScript you would see a list of ten 9s. In ClojureScript we
;; see the expected numbers from 0 to 9.

;j; 上記をJavaScriptでやって見たら、「９」を１０回入ったリストになってしまう。
;j; ClojureScriptでは、「０」から「９」までのリストを予期します。

;; FOOTNOTE:
;;
;; `def` expressions (including `defn`) are always top level. People familiar
;; with Scheme or other Lisps often mistakenly write the following in Clojure:

;j; `def`式(`defn`も含まれて)がいつも、トップレベルです。SchemeやListから来る人は、よく
;j; 下記のようなClojure(Script)を書いてしまいます:

(defn not-scheme []
  (defn no-no-no []))

;; This is almost always incorrect. If you need to write a local function just
;; do it with a let binding.

;j; これは、ほとんどの場合間違えです。ローカルのファンクションを作りたい時は`let`のバインティングを
;j; 使うのがいいでしょう。

(defn outer-fn []
  (let [inner-fn (fn [])]))


;; Destructuring
;; ============================================================================

;j; 分配束縛（デストラクチャリング）
;j; ============================================================================

;; In any serious ClojureScript program, there will be significant amounts of
;; data manipulation. Again, we will see that ClojureScript's uniformity
;; pays off.

;j; ClojureScriptのプログラムがちょっとでも大きくなると、データのマニッピュレーションが
;j; 多いでしょう。ClojureScriptの均一性のかいがまたここにある。

;; In ClojureScript anywhere bindings are allowed (like `let` or function
;; parameters), destructuring is allowed. This is similar to the destructuring
;; proposed for ES6, but the system provided in ClojureScript benefits from
;; all the collections supporting uniform access.

;j; ClojureScriptのバインティングできる所すべて、分配束縛ができます。(例えば、`let` やファンクションの引数)
;j; これは、ES6の分配束縛ににています。ただ、ClojureScriptでは、すべてのコレクションが均一性にアクセスできる事によって
;j; このパターンをもっと便利に使える。


;; Sequence destructuring
;; ----------------------------------------------------------------------------

;j; シークエンスの分配束縛
;j; ----------------------------------------------------------------------------

;; Destructuring sequential types is particularly useful.

;j; シーケンシャルなタイプの分配束縛が特に役に立ちます。

(let [[f & r] '(1 2 3)]
  f)

(let [[f & r] '(1 2 3)]
  r)

(let [[r g b] [255 255 150]]
  g)

;; _ is just a convention for saying that you are not interested at the
;; item in the corresponding position. it has no other special meaning.
;; Here we're only interested at the third local variable named `b`.

;j; _は「このポジションにあるアイテムは興味がないです」という意味の従来です。
;j; それ以外、特別な意味はないです。例えば、下記では、bという引数にしか興味がないです。

(let [[_ _ b] [255 255 150]]
  b)

;; destructuring function arguments works just as well. Here we are
;; only intersted at the second argument `g`.

;j; ファンションの引数にでも、分配束縛我できます。下記は、gという引数が、分配束縛されます。

(defn green [[_ g _]] g)

(green [255 255 150])


;; Map destructuring
;; ----------------------------------------------------------------------------

;j; マップの分配束縛
;j; ----------------------------------------------------------------------------

;; Map destructuring is also useful. Here we destructure the value for the
;; `:foo` key and bind it to a local `f`, and the value for `:baz` key
;; and bind it to a local `b`.

;j; マップの分配束縛も、便利です。下記は、`:foo`キーのバリューをローカル引数 「f」にバインドして、
;j; `baz`キーのバリューをローカル`b`二バインドしています。

(let [{f :foo b :baz} {:foo "bar" :baz "woz"}]
  [f b])

;; If we don't want to rename, we can just use `:keys`.

;j; 別の名前をつけたくなければ、`:keys`というショートカットがあります。

(let [{:keys [first last]} {:first "Bob" :last "Smith"}]
  [first last])

;; The above map destructuring form is very useful when you need to
;; define a function with optional, non positional and defaulted
;; arguments.

;j; 上記のマップの分配束縛を利用して、順番のないデフォルト引数を定義できます。

(defn magic [& {:keys [k g h]
                :or {k 1
                     g 2
                     h 3}}]
  (hash-map :k k
            :g g
            :h h))

(magic)
(magic :k 10)
(magic :g 100)
(magic :h 1000)
(magic :k 10 :g 100 :h 1000)
(magic :h 1000 :k 10 :g 100)

;; Sequences
;; ============================================================================

;; シークエンス
;; ============================================================================

;; We said that ClojureScript data structures are to be preferred as they
;; provide a uniform interface. All ClojureScript collections satisfy
;; the ISeqable protocol, which means iteration is uniform
;; (i.e. polymorphic) for all collection types.

;j; 先程も書きましたが、ClojureScriptのデータ構造が統一されたインターフェイスため、
;j; 優先されるべきです。ClojureScriptのコレクションがすべてISeqableのProtocolを
;j; 満たしていますので、コレクションタイプのすべての反復も、統一されています。


;; Map / Filter / Reduce
;; ----------------------------------------------------------------------------

;j; Map / Filter / Reduce
;j; ----------------------------------------------------------------------------

;; ClojureScript supports the same bells and whistles out of the box that you may
;; be familiar with from other functional programming languages or JavaScript
;; libraries such as Underscore.js

;j; 他の関数型言語や、Underscore.jsのような、JavaScriptのライブラリーが提供する機能がすでに
;j; 使えるようになっています。

(map inc [0 1 2 3 4 5 6 7 8 9])

(filter even? (range 10))

(remove odd? (range 10))

;; ClojureScript's `map` and `filter` operations are lazy. You can stack up
;; operations without getting too concerned about multiple traversals.

;j; ClojureScriptの`map`と`filter`が遅延評価となります。どんどん、操作を重ねても、
;j; 複数の横断は行わないです。

(map #(* % %) (filter even? (range 20)))

(reduce + (range 100))


;; List comprehensions
;; ----------------------------------------------------------------------------

;j; リスト内包表記
;j; ----------------------------------------------------------------------------

;; ClojureScript supports the list comprehensions you might know from various
;; languages. List comprehensions are sometimes more natural or more readable
;; than a chain of `map` and `filter` operations.

;j; 他の言語と同じく、ClojureScriptもリスト内包表記を定義しています。`map`と`filter`を連続で
;j; 書くより、リスト内包表記が読みやすい場合もあるでしょう。

(for [x (range 1 10)
      y (range 1 10)]
  [x y])

(for [x (range 1 10)
      y (range 1 10)
      :when (and (zero? (rem x y))
                 (even? (quot x y)))]
  [x y])

(for [x (range 1 10)
      y (range 1 10)
      :let [prod (* x y)]]
  [x y prod])


;; Seqable collections
;; ----------------------------------------------------------------------------

;j; 「Seqable」コレクション
;j; ----------------------------------------------------------------------------

;; Most ClojureScript collections can be coerced into sequences.

;; ClojureScriptのほとんどのコレクションがシークエンス化出来ます。

(seq {:foo "bar" :baz "woz"})
(seq #{:cat :dog :bird})
(seq [1 2 3 4 5])
(seq '(1 2 3 4 5))

;; Many ClojureScript functions will call `seq` on their arguments in order to
;; provide the expected behavior. The following demonstrates that you can
;; uniformly iterate over all the ClojureScript collections!

;j; 多くのClojureScriptのファンクションが、期待される動作を定義するため、渡された関数に`seq`を呼びます。
;j; 下記の例が、ClojureScriptのコレクションどれでも均一に反復でくると言う事を、証明します。


(first {:foo "bar" :baz "woz"})
(rest {:foo "bar" :baz "woz"})

(first #{:cat :dog :bird})
(rest #{:cat :dog :bird})

(first [1 2 3 4 5])
(rest [1 2 3 4 5])

(first '(1 2 3 4 5))
(rest '(1 2 3 4 5))


;; Metadata
;; ============================================================================

;j; メタデータ
;j; ============================================================================

;; All of the ClojureScript standard collections support metadata. Metadata
;; is a useful way to annotate data without affecting equality. The
;; ClojureScript compiler uses this language feature to great effect.

;j; ClojureScriptの一般的なコレクションがすべてメタデータを定義しています。メタデータが
;j; 平等に影響なく、注釈を付る事が出来ます。ClojureScriptのコンパイラーがメタデータを用いて、
;j; 優れた結果を得ます。

;; You can add metadata to a ClojureScript collection with `with-meta`. The
;; metadata must be a map.

;j; メタデータをClojureScriptのコレックションに`with-meta`で付けます。メタデータは、
;j; mapであるべきです。

(def plain-data [0 1 2 3 4 5 6 7 8 9])

(def decorated-data (with-meta plain-data {:url "http://lighttable.com"}))

;; Metadata has no effect on equality.

;j; メタデータは平等に影響は無いです。

(= plain-data decorated-data)

;; You can access metadata with `meta`.

;j; そして、メタデータは`meta`でアクセス出来ます。

(meta decorated-data)


;; Error Handling
;; ============================================================================

;j; エラー対応
;j; ============================================================================

;; Error handling in ClojureScript is relatively straightforward and more or
;; less similar to what is offered in JavaScript.

;; ClojureScriptでのエラー対応はJavaScriptに似ていて、単純です。

;; You can construct an error like this.

;j; 下記のようにエラーを作れます。

(js/Error. "Oops")

;; You can throw an error like this.
;; (Highlight  the `throw` form below)

;j; そして、このようにエラーを投げる事できます。
;j; (`throw`形式を選択して、評価してください。)

(comment
  (throw (js/Error. "Oops"))
  )

;; You can catch an error like this.

;j; 投げたエラーをこのようにキャッチできます。

(try
  (throw (js/Error. "Oops"))
  (catch js/Error e
    e))

;; JavaScript unfortunately allows you to throw anything. You can handle
;; this in ClojureScript with the following.

;j; 残念ながら、JavaScriptはどのバリューでも投げる事ができます。ClojureScriptでは、このように対応できます。

(try
  (throw (js/Error. "Oops"))
  (catch :default e
    e))

;; Catches are optional. You can also use multiple forms to handle different types of errors.

;j; `catch`するかしないのは、任意です。多数のキャッチを使って、違うエラーのタイプを対応する事はできます。

(try
  (throw (js/Error. "Oops"))
  (catch js/Error e
    e)
  (catch Error e
    e)
  (finally
     "Cleanup here"))


;; Mutation
;; ============================================================================

;j; ミューテーション
;j; ============================================================================

;; Atoms
;; ----------------------------------------------------------------------------

;j; アトム
;j; ----------------------------------------------------------------------------

;; A little bit of mutability goes a long way. ClojureScript does not offer
;; any traditional mutable data structures, however it does support identities
;; that can evolve over time via `atom`.

;j; 少しなミュータビリティは大きな成果を生み出す。ClojureScriptは、普段ミュータブルなデータ構造を提供していないです。
;j; しかし、`atom`では、時間とともに変化する変数を提供しています。

(def x (atom 1))

;; You can dereference the value of an atom with `@`.

;j; アトムのバリューは、`@`で参照できます。

@x

;; This is equivalent to calling `deref`.

;j; これは、`deref`を呼ぶと同じです。

(deref x)

;; If you want to change the value of an atom you can use `reset!` which returns
;; the new value. It's idiomatic to add the bang char `!` at the end of function
;; names mutating objects.

;j; アトムのバリューを変更したいときは、`reset`を利用できます。変数をミューテーションさせる
;j; ファンクションの名前に「!」を付けるのが慣用的です。

(reset! x 2)

x

@x

;; swap!
;; ------------------------------------------------------------------------------


;j; swap!
;j; ------------------------------------------------------------------------------

;; If you want to change the value of an atom on the basis of its current value,
;; you can use `swap!`. In its simplest form `swap!` accept as first argument
;; the atom itself and as a second argument an updating function of one argument
;; which will be instantiated with the current value of the atom. `swap!` returns
;; the new value of the atom.

;; アトムのバリューを現在のバリューによって、変更したい場合`swap!`を利用できます。一番単純な形ですと、
;; `swap`は一つ目の変数で「変更したいアトム」、そして、二つ目の変数で「アトムをアップデートするファンクション」を
;; 受け取とります。`swap!` は、アトムの新しいバリューを返します。

(swap! x inc)

x

@x

;; If your updating function needs extra arguments to calculate the new value, you
;; have to pass them as extra arguments to `swap!` after the updating function
;; itself.

;j; もしアップデートするファンクションが新しいバリューを計算するには、他の変数が必要となるなら、
;j; `swap!`実態にファンクションの後に渡せます。

(swap! x (fn [old extra-arg]
           (+ old extra-arg)) 39)

x

@x

;; As usual when anonymous functions are simple enough, it's idiomatic to use
;; the condensed form.

;j; もちろん、いつも通りに無名のファンクションも渡せる事が可能です。

(swap! x #(- %1 %2) 42)

x

@x

;; Note that the updating function has to be free of side-effects because a
;; waiting writer could call it more than once in a spin loop.

;j; アップデートするファンクションは副作用がない事を気をつけてください。writerが自分の順番
;j; 待っている間、ループで数回呼ばせる可能性があります。


;; set!
;; ----------------------------------------------------------------------------


;j; set!
;j; ----------------------------------------------------------------------------

;; Sometimes you need to mutate existing JavaScript objects. For this you
;; have `set!`.

;; JavaScriptのobjectを変異したい特もあります。この場合、`set!`を利用します。

(def c (.createElement js/document "canvas"))
(def ctxt (.getContext c "2d"))

;; We can use property access with `set!` to change the fill color of a
;; a canvas rendering context.

;; プロパティのアクセスと`set!`を使って、キャンバスのコンテキストのfillの色を変異できます。

(set! (.-fillColor ctxt) "#ffffff")


;; The ClojureScript Standard Library
;; ============================================================================

;j; ClojureScriptのスタンダードライブラリー
;j; ============================================================================

;; The ClojureScript standard library largely mirrors the Clojure standard
;; library with the exception of functionality that assumes a multithreaded
;; environment, first class namespaces, and Java numerics.

;j; ClojureScriptのスタンダードライブラリーが主に、Clojureのスタンダードライブラリーを
;j; 反映しています。だだ、マルチスレッドの環境や、ネイムスペーソが第一級や、Javaの数字など
;j; を期待している所がぬけています。

;; Here are some highlights and patterns that newcomers to ClojureScript might
;; find useful. Remember you can type Control-Shift-D at anytime to bring up
;; the documentation panel to see what any of these function do.

;j; 下記はClojureScriptの初心者に便利なパターンやハイライトを書いてあります。気になったら、
;j; いつでも、「Control-Shift-D」で、ドキュメンテーションのパネルを出して、ダンクションについて
;j; 調べる事ができます。

(apply str (interpose ", " ["Bob" "Mary" "George"]))

((juxt :first :last) {:first "Bob" :last "Smith"})

(def people [{:first "John" :last "McCarthy"}
             {:first "Alan" :last "Kay"}
             {:first "Joseph" :last "Licklider"}
             {:first "Robin" :last "Milner"}])

(map :first people)

(take 5 (repeat "red"))

(take 5 (repeat "blue"))

(take 5 (interleave (repeat "red") (repeat "blue")))

(take 10 (cycle ["red" "white" "blue"]))

(partition 2 [:a 1 :b 2 :c 3 :d 4 :e 5])

(partition 2 1 [:a 1 :b 2 :c 3 :d 4 :e 5])

(take-while #(< % 5) (range 10))

(drop-while #(< % 5) (range 10))


;; Protocols
;; ============================================================================

;j; プロトコル
;j; ============================================================================

;; The ClojureScript language is constructed on a rich set of protocols. The
;; same uniformity provided by ClojureScript collections can be extended to
;; your own types or even types that you do not control!

;j; ClojureScriptがだいたい豊富なプロトコルの上でできています。そしたら、自分のタイプでも、
;j; コントロルしてないタイプでも、ClojureScriptのコレクションが提供する均一性を、
;j; 持って行ける事は可能です！

;; A lot of the uniform power we saw early was because the ClojureScript
;; collections are implemented in terms of protocols. Collections can be
;; coerced into sequences because they implement ISeqable. You can use `get`
;; on vectors and maps because they implement ILookup.

;; 以前、均一性から生まれてくるパワーがコレクションがプロトコルでできているのが原因です。
;; コレクションがシークエンスに変換させれるのが、ISeqableを実装しているだからです。
;; ベクトルとマップに`get`を使えるのは、両帆がILookupを実装しているだからです。

(get {:foo "bar"} :foo)
(get [:cat :bird :dog] 1)

;; Map destructing actually desugars into `get` calls. That means if you extend
;; your type to ILookup it will also support map destructuring!

;; マップの分配束縛が実は、`get`のコールにデシュガーします。とすると、自分のタイプがILookupを
;; 定義していると、


;; extend-type
;; ----------------------------------------------------------------------------

;; ClojureScript supports custom extension to types that avoid many of the
;; pitfalls that you encounter in other languages. For example imagine we have
;; some awesome polymorphic functionality in mind.

(defprotocol MyProtocol (awesome [this]))

;; It's idiomatic to name the first argument of a protocol's functions
;; as `this` which reminds you that it is the argument used by
;; ClojureScript to dispatch the right function implementation on the
;; basis of the type of the value of `this`

;; Now imagine we want JavaScript strings to participate. We can do this
;; simply.

(extend-type string
  MyProtocol
  (awesome [this] (vector this "Totally awesome!")))

(awesome "Is this awesome?")


;; extend-protocol
;; ----------------------------------------------------------------------------

;; Sometimes you want to extend several types to a protocol at once. You can
;; use extend-protocol for this. extend-protocol simply desugars into multiple
;; extend-type forms.

;; As said while learning about `let` special form, when we're not
;; interested in the value of an argument it's idiomatic to use the
;; underscore as a placeholder like above.

(extend-protocol MyProtocol
  js/Date
  (awesome [_] "Having an awesome time!")
  number
  (awesome [_] "I'm an awesome number!"))

(awesome #inst "2014")
(awesome 5)


;; reify
;; ----------------------------------------------------------------------------

;; Sometimes it's useful to make an anonymous type which implements some
;; various protocols.

;; For example say we want a JavaScript object to support ILookup. Now we don't
;; want to blindly `extend-type object`, that would pollute the behavior of plain
;; JavaScript objects for everyone.

;; Instead we can provide a helper function that takes an object and returns
;; something that provides this functionality.

(defn ->lookup [obj]
  (reify
    ILookup
    (-lookup [this k]
      (-lookup this k nil))
    (-lookup [this k not-found]
      (let [k (name k)]
        (if (.hasOwnProperty obj k)
          (aget obj k)
          not-found)))))

;; We can then selectively make JavaScript objects work with `get`.

(get (->lookup #js {"foo" "bar"}) :foo)

;; But this also means we get destructuring on JavaScript objects.

(def some-object #js {"foo" "bar" "baz" "woz"})

(let [{:keys [foo baz]} (->lookup some-object)]
  [foo baz])


;; specify
;; ----------------------------------------------------------------------------

;; Light Table ships with a older version of ClojureScript and does not yet
;; support specify


;; Macros
;; ============================================================================


;; Types & Records
;; ============================================================================

;; deftype
;; ----------------------------------------------------------------------------

;; Sometimes a map will simply not suffice, in these cases you will want to
;; make your own custom type.

(deftype Foo [a b])

;; It's idiomatic to use CamelCase to name a `deftype`. You can instantiate a
;; deftype instance using the same constructor pattern we've already discussed.

(Foo. 1 2)

;; You can access properties of a deftype instance using property access
;; syntax.

(.-a (Foo. 1 2))

;; You can implement protocol methods on a deftype. Note that the first
;; argument to any deftype or defrecord method is the instance itself.
;; The dash in `-count` has no special meaning. It's just a convention for
;; the core ClojureScript protocols. You need not adopt it.

(deftype Foo [a b]
  ICounted
  (-count [this] 2))

(count (Foo. 1 2))

;; Sometimes it's useful to implement methods directly on the deftype.

(deftype Foo [a b]
  Object
  (toString [this] (str a ", " b)))

(.toString (Foo. 1 2))

;; deftype fields are immutable unless specified. The following will not compile.
;; (To prove it to yourself, highlight  the `deftype` form below.)

(comment

  (deftype Foo [a ^:mutable b]
    Object
    (setA [this val] (set! a val)))

  )

;; The following will compile.

(deftype Foo [a ^:mutable b]
  Object
  (setB [this val] (set! b val)))


;; defrecord
;; ----------------------------------------------------------------------------

;; `deftype` doesn't provide much out of the box. Often what you want to do is
;; have a domain object that acts more or less like a map. This is what
;; `defrecord` is for.

;; Like `deftype`, it's idiomatic to use CamelCase to name a `defrecord`.

(defrecord Person [first last])

;; You can construct an instance in the usual way.

(Person. "Bob" "Smith")

;; Or you can use the provided constructors.

(->Person "Bob" "Smith")

(map->Person {:first "Bob" :last "Smith"})

;; It's considered idiomatic (and recommended) to define a factory function
;; which returns the created instance of a defrecord/deftype. It's idiomatic to use
;; dash-case for factories names.

(defn person [first last]
  (->Person first last))

;; records work like maps

(seq (person "Bob" "Smith"))

(:first (person "Bob" "Smith"))

(keys (person "Bob" "Smith"))

(vals (person "Bob" "Smith"))

;; both deftype and defrecord are open to dynamic extensions (i.e. open class)

(keys (assoc (person "Bob" "Smith") :age 18))


;; Records & Protocols
;; ----------------------------------------------------------------------------

;; You can extend a defrecord to satisfy a protocol as you do with deftype.

(extend-type Person
  MyProtocol
  (awesome [this]
    (str (:last this) ", " (:first this))))

(awesome (person "Bob" "Smith"))

(satisfies? MyProtocol (person "Bob" "Smith"))

;; Or you can extend a protocol on a defrecord.

(extend-protocol MyProtocol
  Person
  (awesome [this]
    (str (:last this) ", " (:first this))))

(awesome (person "Bob" "Smith"))

(satisfies? MyProtocol (person "Bob" "Smith"))

;; If you need a more sophisticated form of polymorphism consider multimethods.

;; If you mix types/records with protocols you are modeling your problem with an
;; object oriented approach, which is sometimes useful.

;; Note ClojureScript does not offer a direct form of inheritance. Instead,
;; reuse/extension by composition is encouraged. It's best to avoid
;; deftype/defrecord and model your problem with plain maps. You can easily
;; switch to records later on down the line.

(defrecord Contact [person email])

;; Even if it's not required, remember to define a factory function to create
;; instances of the new Contact record type by internally calling the factory
;; function for the Person record type.

(defn contact [first last email]
  (->Contact (person first last) email))

(contact "Bob" "Smith" "bob.smith@acme.com")

;; And extend the protocol on defrecord as well.

(extend-protocol MyProtocol
  Contact
  (awesome [this]
    (str (awesome (:person this)) ", " (:email this))))

(awesome (contact "Bob" "Smith" "bob.smith@acme.com"))

;; To change the value of a nested key you use 'assoc-in', like with maps.

(assoc-in (contact "Bob" "Smith" "bob.smith@acme.com")
          [:person :first] "Robert")

;; If you need to use the previous value of a nested field for calculating the
;; new one, you can use 'update-in', like with maps.

(update-in (contact "Bob" "Smith" "bob.smith@acme.com")
           [:person :first] #(string/replace %1 #"Bob" %2) "Robert")

;; As said, the main difference with the majority of OO languages is that your
;; instances of deftypes/defrecords are immutable.

(def bob (contact "Bob" "Smith" "bob.smith@acme.com"))

(update-in bob [:person :first] #(string/replace %1 #"Bob" %2) "Robert")

(get-in bob [:person :first])


;; JavaScript Interop
;; ============================================================================

;; Property Access
;; ----------------------------------------------------------------------------

(def a-date (js/Date.))

;; You can access properties with the `.-` property access syntax.

(.-getSeconds a-date)


;; Method Calls
;; ----------------------------------------------------------------------------

;; Methods can be invoked with the `.` syntax.

(.getSeconds a-date)

;; The above desugars into the following.

(. a-date (getSeconds))

;; For example, you can write a `console.log` call like so.

(. js/console (log "Interop!"))


;; Primitive Array Operations
;; ----------------------------------------------------------------------------

;; When writing performance sensitive code, sometimes dealing with mutable
;; arrays is unavoidable. ClojureScript provides a variety of functions for
;; creating and manipulating JavaScript arrays.

;; You can make an array of specific size with `make-array`

(make-array 32)

;; You can access an element of an array with `aget`.

(aget #js ["one" "two" "three"] 1)

;; You can access nested arrays with `aget`.

(aget #js [#js ["one" "two" "three"]] 0 1)

;; You can set the contents of an array with aset.

(def yucky-stuff #js [1 2 3])

(aset yucky-stuff 1 4)

yucky-stuff
