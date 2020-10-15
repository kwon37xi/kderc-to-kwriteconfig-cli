# kderc-to-kwriteconfig-cli
  * KDE 설정파일을 읽이서 전체를 kwriteconfig 명령으로 출력한다.
  * 수작업으로 설정한 파일을 명령행으로 변환하고자 할 때 사용한다.
  
## Requirements
* Java 8

## Usage
```
cd kderc-to-kwriteconfig-cli/bin

./kderc-to-kwriteconfig-cli <path-to-rc-file>

# example
./kderc-to-kwriteconfig-cli ~/.config/kglobalshortcutsrc
```

## build distributions
```
./gradlew clean assembleDist

# build/distributions 에 압축파일 생성됨.
```