-- ユーザー認証情報テーブル
CREATE TABLE IF NOT EXISTS user_accounts (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL UNIQUE,
    password CHAR(60) NOT NULL,
    name VARCHAR(20) NOT NULL,
    role_id SMALLINT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- ユーザー情報テーブル
CREATE TABLE IF NOT EXISTS user_profiles (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL UNIQUE,
    department_id INTEGER NOT NULL,
    workplace_id INTEGER NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_profiles_account FOREIGN KEY (user_id) REFERENCES user_accounts (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_profiles_department FOREIGN KEY (department_id) REFERENCES departments (department_id),
    CONSTRAINT fk_profiles_workplace FOREIGN KEY (workplace_id) REFERENCES workplaces (workplace_id)
);

-- 勤怠記録テーブル
CREATE TABLE IF NOT EXISTS attendance_records (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    record_id BIGINT NOT NULL UNIQUE,
    user_id VARCHAR(50) NOT NULL,
    work_date DATE NOT NULL,
    clockin_time TIMESTAMPTZ,
    clockout_time TIMESTAMPTZ,
    workplace_id INTEGER NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_attendance_account FOREIGN KEY (user_id) REFERENCES user_accounts (user_id),
    CONSTRAINT fk_attendance_workplace FOREIGN KEY (workplace_id) REFERENCES workplaces (workplace_id)
);

CREATE INDEX IF NOT EXISTS idx_attendance_user_date ON attendance_records (user_id, work_date);
