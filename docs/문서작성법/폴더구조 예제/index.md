
폴더구조 사용법

1. 디렉토리를 만든다. 이름은 상관없다.
2. 아래 내용을 디렉토리 안의 index.md 를 만들어서 넣는다.
```text
---
layout: default
title: 폴더구조 예제
nav_order: 3
has_children: true // 디렉토리 구조로 할 것인가 
---
```
2. 디렉토리 안에 파일을 만든 후 아래 내용을 넣는다.
```text
---
layout: default
title: 1번
parent: 폴더구조 예제 // 부모 디렉토리의 title. 실제 디렉토리 명이 아닌 title에 입력된 글자를 넣어야 한다.
nav_order: 1 // 디렉토리 안에서의 순서
---
```
3. 3단계 까지 만들 수 있다. 다만, 3단계는 방법이 다르다.
    자세한건 https://just-the-docs.github.io/just-the-docs/docs/navigation-structure/#child-pages