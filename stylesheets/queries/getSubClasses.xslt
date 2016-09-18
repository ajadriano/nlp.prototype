<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/ROOT/SBARQ[SQ/VP/(VB|VBZ|VBP)/@id=@root and WHNP[WHNP/NN[@lemma='kind'] and PP[IN[@lemma='of'] and NP[NN|NNS]]] and SQ[VP[(VB|VBZ|VBP)[@lemma!='do']]]]">
GetSubClasses(ObjectIntersectionOf(
	<xsl:apply-templates select="WHNP/PP/NP"/>
        <xsl:text> </xsl:text>
        <xsl:apply-templates select="SQ/VP"/>))
</xsl:template>

<xsl:template match="/ROOT/SBARQ[SQ/VP/(VB|VBZ|VBP)/@id=@root and WHNP[WHNP/NN[@lemma='type'] and PP[IN[@lemma='of'] and NP[NN|NNS]]] and SQ[VP[(VB|VBZ|VBP)[@lemma!='do']]]]">
GetSubClasses(ObjectIntersectionOf(
	<xsl:apply-templates select="WHNP/PP/NP"/>
        <xsl:text> </xsl:text>
        <xsl:apply-templates select="SQ/VP"/>))
</xsl:template>

</xsl:stylesheet>