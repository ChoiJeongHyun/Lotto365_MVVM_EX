package kr.co.example.lotto365.commonset.components

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kr.co.example.lotto365.miniframework.database.dbmanager.AppDatabase
import kr.co.example.lotto365_mvp.MiniFrameWork.Manager.FontManager
import kr.co.example.lotto365_mvvm.di.component.DaggerAppComponent
import kr.co.example.lotto365_mvvm.di.module.AppModule
import kr.co.example.lotto365_mvvm.miniframework.DataBase.PrefDatabase
import javax.inject.Inject


class LottoApplication : MultiDexApplication(), HasAndroidInjector {

    private val dreamMap = LinkedHashMap<String, ArrayList<String>>()
    private val dreamNumberMap = HashMap<String, String>()

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any>? = dispatchingAndroidInjector


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).appModule(AppModule(this)).build()
            .inject(this)
        initLoader()

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initLoader() {
        initModules()
        setDreamMap()
        setDreamNumberMap()
        FontManager.init(this)
    }

    private fun initModules() {
        AppDatabase.init(this)
    }

    open fun getDreamMap() = dreamMap

    open fun getDreamNumberMap() = dreamNumberMap


    private fun setDreamMap() {

        dreamMap["자연"] = ArrayList(
            arrayListOf(
                "빙산", "거미줄", "물", "산", "바다", "밭", "태풍", "하늘", "비", "구름", "모래"
            )
        )

        dreamMap["사물"] = ArrayList(
            arrayListOf(
                "똥",
                "담배",
                "상복",
                "의자",
                "이불",
                "면허증",
                "치약",
                "고무인형",
                "권총",
                "동전",
                "리어카",
                "소방복",
                "책상",
                "청소기",
                "피아노",
                "중고차",
                "축구공",
                "놀이기구",
                "카메라",
                "승용차",
                "신문지",
                "자동차",
                "작업차",
                "철가방",
                "셔터문",
                "잡동사니",
                "세숫대야",
                "자동차키",
                "머플러",
                "작은트럭",
                "창문",
                "펌프카",
                "양복",
                "바닥담요",
                "장롱",
                "컴퓨터",
                "유리창",
                "옷고름",
                "오토바이",
                "중곡옷",
                "검은봉지",
                "케이블선",
                "흰옷",
                "돈",
                "천원",
                "가구",
                "만원",
                "불",
                "버스",
                "벽",
                "칼",
                "삽",
                "사진",
                "지갑",
                "조끼",
                "복숭아씨",
                "말뚝박기",
                "일회용기저귀",
                "호스",
                "전화",
                "양말",
                "나무공예",
                "디지털카메라",
                "로또",
                "택시",
                "트럭",
                "껍질",
                "도끼빗",
                "스티커",
                "전기선",
                "지폐",
                "칫솔",
                "볼펜",
                "보따리",
                "시줏돈",
                "짐",
                "모자",
                "수도꼭지",
                "시계",
                "상",
                "손목시계",
                "옷",
                "토끼풀",
                "우산"
            )
        )

        dreamMap["음식"] = ArrayList(
            arrayListOf(
                "라면",
                "안주",
                "옥수수",
                "단무지",
                "제사상",
                "음식",
                "양주",
                "냉면",
                "음료수",
                "밥",
                "아이스크림",
                "소스",
                "가지",
                "수박",
                "바나나",
                "돈까스",
                "국수",
                "소금",
                "감자",
                "고구마"
            )
        )

        dreamMap["동물"] = ArrayList(
            arrayListOf(
                "물고기",
                "악어",
                "암컷",
                "쥐",
                "악어새끼",
                "고양이",
                "검은돼지",
                "금돼지",
                "거북이",
                "원숭이",
                "구렁이",
                "거미",
                "말",
                "뱀",
                "개미"
            )
        )

        dreamMap["사람"] = ArrayList(
            arrayListOf(
                "군인",
                "여자",
                "아버지",
                "아가씨",
                "아들",
                "부처님",
                "얼굴",
                "아저씨",
                "청년",
                "경찰",
                "외할머니",
                "남편",
                "이모",
                "동창",
                "범인",
                "아이",
                "엄마",
                "남자친구",
                "성인",
                "동생",
                "임신부",
                "스포츠머리",
                "여자아이",
                "아는언니",
                "바람둥이",
                "할머니",
                "선배",
                "친구",
                "아주머니",
                "여자동기",
                "사촌동생",
                "아는여자",
                "연예인",
                "남자아이",
                "ROTC",
                "남동생",
                "젋은이",
                "형",
                "수사관",
                "회사동료",
                "스님",
                "아기",
                "좀비",
                "처남",
                "거인",
                "남자"
            )
        )

        dreamMap["신체"] = ArrayList(
            arrayListOf(
                "무릎",
                "어깨",
                "아랫니",
                "윗니",
                "생식기",
                "단발머리",
                "이빨",
                "귀",
                "손",
                "발",
                "나체",
                "팔",
                "여드름",
                "팔뒷꿈치",
                "목",
                "가슴",
                "등",
                "손목",
                "입",
                "덧니",
                "어금니"
            )
        )

        dreamMap["장소"] = ArrayList(
            arrayListOf(
                "시장",
                "사우나",
                "공터",
                "거북이등산",
                "식당",
                "옷가게",
                "통로",
                "과수원",
                "무역회사",
                "신한은행",
                "우리집",
                "놀이공원",
                "아파트",
                "밭두렁",
                "노천극장",
                "낭떠러지",
                "마당",
                "암벽",
                "방송국",
                "대형슈퍼",
                "작은섬",
                "장례식장",
                "전시회",
                "대학교",
                "지하철",
                "결혼식",
                "운동장",
                "발표회",
                "좁은골목",
                "계단",
                "대전",
                "숲속",
                "섬",
                "초등학교",
                "터널",
                "사거리",
                "교회",
                "시골길",
                "지하주차장",
                "교도소",
                "돌다리",
                "방",
                "무대",
                "고향집",
                "집",
                "가게",
                "길가",
                "화장실"
            )
        )

        dreamMap["행동"] = ArrayList(
            arrayListOf(
                "물다",
                "매매",
                "정리",
                "혼인서약",
                "놀람",
                "대입시험",
                "교통사고",
                "애무",
                "싸움",
                "머리감기",
                "목욕",
                "신체검사",
                "취직",
                "빨래",
                "축구",
                "잠",
                "전화통화",
                "성폭행",
                "짝짓기",
                "이사",
                "인사",
                "찌름",
                "위협",
                "화장",
                "고개끄덕",
                "분노",
                "승리",
                "키스",
                "납치",
                "낚시"
            )
        )

        dreamMap["기타"] = ArrayList(
            arrayListOf(
                "깨짐",
                "실종",
                "왼쪽",
                "붉은갈색",
                "인상",
                "외상값",
                "표범문신",
                "공양",
                "결론",
                "종양",
                "곰팡이",
                "허공",
                "발자국",
                "꿈",
                "미소",
                "돼지코",
                "영화",
                "누런",
                "복",
                "죽음",
                "초록",
                "피",
                "젖",
                "커브",
                "코너",
                "때",
                "단서",
                "소변",
                "수컷",
                "전기",
                "주황색"
            )
        )


    }

    private fun setDreamNumberMap() {
        dreamNumberMap["똥"] = "16"
        dreamNumberMap["담배"] = "16"
        dreamNumberMap["상복"] = "16";
        dreamNumberMap["의자"] = "16"
        dreamNumberMap["이불"] = "16"
        dreamNumberMap["면허증"] = "17"
        dreamNumberMap["치약"] = "17"
        dreamNumberMap["고무인형"] = "18"
        dreamNumberMap["권총"] = "18"
        dreamNumberMap["동전"] = "18"
        dreamNumberMap["리어카"] = "18"
        dreamNumberMap["소방복"] = "18"
        dreamNumberMap["책상"] = "18"
        dreamNumberMap["청소기"] = "18"
        dreamNumberMap["피아노"] = "18"
        dreamNumberMap["중고차"] = "19"
        dreamNumberMap["축구공"] = "19"
        dreamNumberMap["놀이기구"] = "20"
        dreamNumberMap["카메라"] = "20"
        dreamNumberMap["승용차"] = "20"
        dreamNumberMap["신문지"] = "20"
        dreamNumberMap["자동차"] = "20"
        dreamNumberMap["작업차"] = "20"
        dreamNumberMap["철가방"] = "20"
        dreamNumberMap["셔터문"] = "22"
        dreamNumberMap["잡동사니"] = "22"
        dreamNumberMap["세숫대야"] = "23"
        dreamNumberMap["자동차키"] = "23"
        dreamNumberMap["머플러"] = "24"
        dreamNumberMap["작은트럭"] = "26"
        dreamNumberMap["창문"] = "26"
        dreamNumberMap["펌프카"] = "26"
        dreamNumberMap["양복"] = "27"
        dreamNumberMap["바닥담요"] = "28"
        dreamNumberMap["장롱"] = "28"
        dreamNumberMap["컴퓨터"] = "28"
        dreamNumberMap["유리창"] = "30"
        dreamNumberMap["옷고름"] = "30"
        dreamNumberMap["오토바이"] = "30"
        dreamNumberMap["중국옷"] = "30"
        dreamNumberMap["검은봉지"] = "1"
        dreamNumberMap["케이블선"] = "1"
        dreamNumberMap["흰옷"] = "2"
        dreamNumberMap["돈"] = "3"
        dreamNumberMap["천원"] = "3"
        dreamNumberMap["가구"] = "4"
        dreamNumberMap["만원"] = "4"
        dreamNumberMap["불"] = "4"
        dreamNumberMap["버스"] = "4"
        dreamNumberMap["벽"] = "4"
        dreamNumberMap["칼"] = "5"
        dreamNumberMap["삽"] = "6"
        dreamNumberMap["사진"] = "6"
        dreamNumberMap["지갑"] = "6"
        dreamNumberMap["조끼"] = "6"
        dreamNumberMap["복숭아씨"] = "31"
        dreamNumberMap["말뚝박기"] = "32"
        dreamNumberMap["일회용기저귀"] = "34"
        dreamNumberMap["호스"] = "34"
        dreamNumberMap["전화"] = "37"
        dreamNumberMap["양말"] = "38"
        dreamNumberMap["나무공예"] = "44"
        dreamNumberMap["디지털카메라"] = "44"
        dreamNumberMap["로또"] = "45"
        dreamNumberMap["택시"] = "8"
        dreamNumberMap["트럭"] = "8"
        dreamNumberMap["껍질"] = "9"
        dreamNumberMap["도끼빗"] = "9"
        dreamNumberMap["스티커"] = "9"
        dreamNumberMap["전기선"] = "9"
        dreamNumberMap["지폐"] = "9"
        dreamNumberMap["칫솔"] = "9"
        dreamNumberMap["볼펜"] = "10"
        dreamNumberMap["보따리"] = "10"
        dreamNumberMap["시줏돈"] = "10"
        dreamNumberMap["짐"] = "10"
        dreamNumberMap["모자"] = "12"
        dreamNumberMap["수도꼭지"] = "12"
        dreamNumberMap["시계"] = "12"
        dreamNumberMap["상"] = "13"
        dreamNumberMap["손목시계"] = "13"
        dreamNumberMap["옷"] = "13"
        dreamNumberMap["토끼풀"] = "13"
        dreamNumberMap["우산"] = "15"

        dreamNumberMap["빙산"] = "16"
        dreamNumberMap["거미줄"] = "20"
        dreamNumberMap["물"] = "3"
        dreamNumberMap["산"] = "3"
        dreamNumberMap["바다"] = "5"
        dreamNumberMap["밭"] = "5"
        dreamNumberMap["태풍"] = "33"
        dreamNumberMap["하늘"] = "36"
        dreamNumberMap["비"] = "11"
        dreamNumberMap["구름"] = "14"
        dreamNumberMap["모래"] = "14"

        dreamNumberMap["라면"] = "16"
        dreamNumberMap["안주"] = "16"
        dreamNumberMap["옥수수"] = "17"
        dreamNumberMap["단무지"] = "18"
        dreamNumberMap["제사상"] = "19"
        dreamNumberMap["음식"] = "24"
        dreamNumberMap["양주"] = "27"
        dreamNumberMap["냉면"] = "28"
        dreamNumberMap["음료수"] = "30"
        dreamNumberMap["밥"] = "3"
        dreamNumberMap["아이스크림"] = "3"
        dreamNumberMap["소스"] = "4"
        dreamNumberMap["가지"] = "5"
        dreamNumberMap["수박"] = "5"
        dreamNumberMap["바나나"] = "6"
        dreamNumberMap["돈까스"] = "8"
        dreamNumberMap["국수"] = "11"
        dreamNumberMap["소금"] = "12"
        dreamNumberMap["감자"] = "14"
        dreamNumberMap["고구마"] = "14"

        dreamNumberMap["물고기"] = "18"
        dreamNumberMap["악어"] = "25"
        dreamNumberMap["암컷"] = "26"
        dreamNumberMap["쥐"] = "4"
        dreamNumberMap["악어새끼"] = "31"
        dreamNumberMap["고양이"] = "38"
        dreamNumberMap["검은돼지"] = "40"
        dreamNumberMap["금돼지"] = "8"
        dreamNumberMap["거북이"] = "8"
        dreamNumberMap["원숭이"] = "8"
        dreamNumberMap["구렁이"] = "9"
        dreamNumberMap["거미"] = "10"
        dreamNumberMap["말"] = "10"
        dreamNumberMap["뱀"] = "12"
        dreamNumberMap["개미"] = "12"

        dreamNumberMap["군인"] = "16"
        dreamNumberMap["여자"] = "16"
        dreamNumberMap["아버지"] = "17"
        dreamNumberMap["아가씨"] = "17"
        dreamNumberMap["아들"] = "17"
        dreamNumberMap["부처님"] = "18"
        dreamNumberMap["얼굴"] = "18"
        dreamNumberMap["아저씨"] = "18"
        dreamNumberMap["청년"] = "18"
        dreamNumberMap["경찰"] = "18"
        dreamNumberMap["외할머니"] = "19"
        dreamNumberMap["남편"] = "20"
        dreamNumberMap["이모"] = "20"
        dreamNumberMap["동창"] = "24"
        dreamNumberMap["범인"] = "24"
        dreamNumberMap["아이"] = "24"
        dreamNumberMap["엄마"] = "24"
        dreamNumberMap["남자친구"] = "26"
        dreamNumberMap["성인"] = "26"
        dreamNumberMap["동생"] = "28"
        dreamNumberMap["임신부"] = "28"
        dreamNumberMap["스포츠머리"] = "30"
        dreamNumberMap["여자아이"] = "30"
        dreamNumberMap["아는언니"] = "30"
        dreamNumberMap["바람둥이"] = "1"
        dreamNumberMap["할머니"] = "3"
        dreamNumberMap["선배"] = "6"
        dreamNumberMap["친구"] = "6"
        dreamNumberMap["아주머니"] = "31"
        dreamNumberMap["여자동기"] = "32"
        dreamNumberMap["사촌동생"] = "34"
        dreamNumberMap["아는여자"] = "36"
        dreamNumberMap["연예인"] = "41"
        dreamNumberMap["남자이이"] = "42"
        dreamNumberMap["ROTC"] = "43"
        dreamNumberMap["남동생"] = "44"
        dreamNumberMap["젋은이"] = "44"
        dreamNumberMap["형"] = "44"
        dreamNumberMap["수사관"] = "8"
        dreamNumberMap["회사동료"] = "8"
        dreamNumberMap["스님"] = "12"
        dreamNumberMap["아기"] = "14"
        dreamNumberMap["좀비"] = "14"
        dreamNumberMap["처남"] = "14"
        dreamNumberMap["거인"] = "15"
        dreamNumberMap["남자"] = "14"

        dreamNumberMap["무릎"] = "18"
        dreamNumberMap["어깨"] = "16"
        dreamNumberMap["윗니"] = "16"
        dreamNumberMap["어금니"] = "26"
        dreamNumberMap["아랫니"] = "19"
        dreamNumberMap["생식기"] = "19"
        dreamNumberMap["단발머리"] = "28"
        dreamNumberMap["이빨"] = "2"
        dreamNumberMap["귀"] = "3"
        dreamNumberMap["손"] = "3"
        dreamNumberMap["발"] = "4"
        dreamNumberMap["나체"] = "6"
        dreamNumberMap["팔"] = "6"
        dreamNumberMap["여드름"] = "32"
        dreamNumberMap["팔뒷꿈치"] = "38"
        dreamNumberMap["목"] = "8"
        dreamNumberMap["가슴"] = "12"
        dreamNumberMap["등"] = "13"
        dreamNumberMap["손목"] = "14"
        dreamNumberMap["입"] = "13"
        dreamNumberMap["덧니"] = "6"

        dreamNumberMap["시장"] = "16"
        dreamNumberMap["사우나"] = "16"
        dreamNumberMap["공터"] = "17"
        dreamNumberMap["거북이등산"] = "17"
        dreamNumberMap["식당"] = "17"
        dreamNumberMap["옷가게"] = "18"
        dreamNumberMap["통로"] = "18"
        dreamNumberMap["과수원"] = "19"
        dreamNumberMap["무역회사"] = "19"
        dreamNumberMap["신한은행"] = "19"
        dreamNumberMap["우리집"] = "19"
        dreamNumberMap["놀이공원"] = "20"
        dreamNumberMap["아파트"] = "20"
        dreamNumberMap["밭두렁"] = "22"
        dreamNumberMap["노천극장"] = "23"
        dreamNumberMap["낭떠러지"] = "24"
        dreamNumberMap["마당"] = "24"
        dreamNumberMap["암벽"] = "26"
        dreamNumberMap["방송국"] = "29"
        dreamNumberMap["대형슈퍼"] = "30"
        dreamNumberMap["작은섬"] = "32"
        dreamNumberMap["장례식장"] = "36"
        dreamNumberMap["전시회"] = "39"
        dreamNumberMap["대학교"] = "40"
        dreamNumberMap["지하철"] = "40"
        dreamNumberMap["결혼식"] = "41"
        dreamNumberMap["운동장"] = "41"
        dreamNumberMap["발표회"] = "42"
        dreamNumberMap["좁은골목"] = "42"
        dreamNumberMap["계단"] = "8"
        dreamNumberMap["대전"] = "8"
        dreamNumberMap["숲속"] = "8"
        dreamNumberMap["섬"] = "8"
        dreamNumberMap["초등학교"] = "8"
        dreamNumberMap["터널"] = "8"
        dreamNumberMap["사거리"] = "9"
        dreamNumberMap["교화"] = "10"
        dreamNumberMap["시골길"] = "10"
        dreamNumberMap["지하주차장"] = "10"
        dreamNumberMap["교도소"] = "11"
        dreamNumberMap["돌다리"] = "11"
        dreamNumberMap["방"] = "13"
        dreamNumberMap["무대"] = "14"
        dreamNumberMap["고향집"] = "15"
        dreamNumberMap["집"] = "4"
        dreamNumberMap["가게"] = "5"
        dreamNumberMap["길가"] = "6"
        dreamNumberMap["화장실"] = "6"

        dreamNumberMap["물다"] = "16"
        dreamNumberMap["매매"] = "17"
        dreamNumberMap["정리"] = "17"
        dreamNumberMap["혼인서약"] = "17"
        dreamNumberMap["놀람"] = "18"
        dreamNumberMap["대입시험"] = "21"
        dreamNumberMap["교통사고"] = "22"
        dreamNumberMap["애무"] = "22"
        dreamNumberMap["싸움"] = "24"
        dreamNumberMap["머리감기"] = "25"
        dreamNumberMap["목욕"] = "26"
        dreamNumberMap["신체검사"] = "26"
        dreamNumberMap["취직"] = "8"
        dreamNumberMap["빨래"] = "9"
        dreamNumberMap["축구"] = "9"
        dreamNumberMap["잠"] = "10"
        dreamNumberMap["전화통화"] = "11"
        dreamNumberMap["성폭행"] = "12"
        dreamNumberMap["짝짓기"] = "12"
        dreamNumberMap["이사"] = "14"
        dreamNumberMap["인사"] = "15"
        dreamNumberMap["찌름"] = "1"
        dreamNumberMap["위협"] = "2"
        dreamNumberMap["화장"] = "2"
        dreamNumberMap["고개끄덕"] = "5"
        dreamNumberMap["분노"] = "5"
        dreamNumberMap["승리"] = "5"
        dreamNumberMap["키스"] = "5"
        dreamNumberMap["납치"] = "6"
        dreamNumberMap["낚시"] = "6"

        dreamNumberMap["깨짐"] = "18"
        dreamNumberMap["실종"] = "18"
        dreamNumberMap["왼쪽"] = "19"
        dreamNumberMap["붉은갈색"] = "26"
        dreamNumberMap["인상"] = "26"
        dreamNumberMap["외상값"] = "30"
        dreamNumberMap["표범문신"] = "32"
        dreamNumberMap["공양"] = "37"
        dreamNumberMap["결론"] = "38"
        dreamNumberMap["종양"] = "38"
        dreamNumberMap["곰팡이"] = "42"
        dreamNumberMap["허공"] = "45"
        dreamNumberMap["발자국"] = "8"
        dreamNumberMap["꿈"] = "10"
        dreamNumberMap["미소"] = "10"
        dreamNumberMap["돼지코"] = "11"
        dreamNumberMap["영화"] = "12"
        dreamNumberMap["누런"] = "3"
        dreamNumberMap["복"] = "3"
        dreamNumberMap["죽음"] = "4"
        dreamNumberMap["초록"] = "4"
        dreamNumberMap["피"] = "4"
        dreamNumberMap["젖"] = "5"
        dreamNumberMap["커브"] = "5"
        dreamNumberMap["코너"] = "5"
        dreamNumberMap["때"] = "6"
        dreamNumberMap["단서"] = "6"
        dreamNumberMap["소변"] = "6"
        dreamNumberMap["수컷"] = "6"
        dreamNumberMap["전기"] = "6"
        dreamNumberMap["주황색"] = "6"


    }


}