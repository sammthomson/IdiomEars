[![Build Status](https://travis-ci.org/sammthomson/IdiomEars.svg?branch=master)](https://travis-ci.org/sammthomson/IdiomEars)

IdiomEars
=========

Idiom brackets à la "Applicative programming with effects", McBride and Paterson.
Allows the following syntax for `Applicative`s like `Option`, `List`, etc.:

```scala
import cats.std.list._
import IdiomEars._
```
```scala
val f = (a: Int) => (b: Int) => a * b
⊏| (f) (List(1, 2)) (List(3, 4)) |⊐
// List(3, 4, 6, 8)
⊏| (f) (List(1)) (Nil) |⊐
// List()
```

which is equivalent to

```scala
Applicative[List].pure(f).ap(List(1)).ap(Nil)
```

Unicode is cute, but annoying to type, so there's the ascii alternative:

```scala
*| (f) (List(1)) (Nil) |*
```

If you're against symbolic operators, which you probably should be, just don't use this.
