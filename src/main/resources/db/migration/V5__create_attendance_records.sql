CREATE TABLE IF NOT EXISTS attendance_records (
    id BIGSERIAL PRIMARY KEY,
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
