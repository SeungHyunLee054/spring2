
// omokView.h: ComokView 클래스의 인터페이스
//

#pragma once


class ComokView : public CView
{
protected: // serialization에서만 만들어집니다.
	ComokView() noexcept;
	DECLARE_DYNCREATE(ComokView)

// 특성입니다.
public:
	ComokDoc* GetDocument() const;

// 작업입니다.
public:

// 재정의입니다.
public:
	virtual void OnDraw(CDC* pDC);  // 이 뷰를 그리기 위해 재정의되었습니다.
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// 구현입니다.
public:
	virtual ~ComokView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// 생성된 메시지 맵 함수
protected:
	DECLARE_MESSAGE_MAP()
public:
	BOOL m_sunseo;
	int m_row, m_col;
	int m_bwin, m_wwin;
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	int CheckVictory(int a, int r, int c);
	afx_msg void OnEditUndo();
};

#ifndef _DEBUG  // omokView.cpp의 디버그 버전
inline ComokDoc* ComokView::GetDocument() const
   { return reinterpret_cast<ComokDoc*>(m_pDocument); }
#endif

