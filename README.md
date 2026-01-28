# Talim mobil ilovasi (o'quv markazlar uchun)

## Maqsad
Ushbu loyiha o'quv markazlari uchun mobil ilovalar to'plamini yaratishni ko'zda tutadi. Tizim ikki asosiy rolga ega bo'ladi:

- **O'qituvchi ilovasi**
- **Talaba (o'quvchi) ilovasi**

## Asosiy funksiyalar

### O'qituvchi uchun
- Topshiriqlar yaratish va guruhlar bo'yicha taqsimlash.
- Deadline belgilash va topshirilgan ishlarni ko'rish.
- Talabalar natijalarini baholash va ballarni kiritish.
- Guruh bo'yicha o'zlashtirish statistikasini ko'rish (tartiblash).

### Talaba uchun
- Topshiriqlar ro'yxatini ko'rish.
- Deadline va topshirilganlik holatini kuzatish.
- Baholar va umumiy ballarni ko'rish.
- Guruhdagi o'z o'rnini ko'rish (o'zlashtirish bo'yicha tartib).

## Nazorat va tahlil tizimi
- Har bir topshiriq bo'yicha bajarilganlik holati.
- Deadline o'tishi yoki yaqinlashishi haqida eslatmalar.
- Ballarni avtomatik hisoblash va guruh bo'yicha saralash.

## Keyingi qadamlar
- Platforma va texnologiya tanlash (Android/iOS, backend).
- Foydalanuvchi oqimlari (user flow) va UX maketlar.
- Minimal funksional prototip (MVP) rejalari.

## Backend MVP (FastAPI)
Quyidagi backend namunasi topshiriqlar, topshirilganlik holati va guruh bo'yicha reytingni ko'rish uchun API beradi.

### Ishga tushirish
```bash
cd backend
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
uvicorn app.main:app --reload
```

### Asosiy endpointlar
- `POST /users` — o'qituvchi yoki talaba yaratish.
- `POST /groups` — guruh yaratish.
- `POST /groups/{group_id}/members` — guruhga talaba qo'shish.
- `POST /assignments` — topshiriq yaratish (faqat o'qituvchi).
- `POST /submissions` — topshirig'ini yuborish (faqat talaba).
- `GET /groups/{group_id}/leaderboard` — guruh bo'yicha ballar reytingi.

## Android MVP (Kotlin + MVVM + Clean Architecture)
Ilova uchun minimal Android skeleti `app/` papkasida joylashgan. U MVVM va Clean Architecture uchun tayyor qatlamlarni beradi.

### Ishga tushirish
1. Android Studio orqali `TalimApp` loyihasini oching.
2. Gradle sinxronizatsiyasini yakunlang.
3. `MainActivity` orqali test qurilmada ishga tushiring.

### Qatlamlar
- `domain` — biznes modellari va use-case lar.
- `data` — Retrofit/Room orqali ma'lumotlar qatlamlari.
- `presentation` — ViewModel va UI holatlari.
